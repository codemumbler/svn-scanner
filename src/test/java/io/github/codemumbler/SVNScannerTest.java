package io.github.codemumbler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.Calendar;
import java.util.Date;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SVNRepositoryFactory.class, SVNWCUtil.class})
public class SVNScannerTest {

	public static final String SVN_URL = "svn://url";
	private SVNScanner scanner;

	@Before
	public void setUp() throws Exception {
		MockSVNRepository repository = new MockSVNRepository(SVNURL.parseURIEncoded(SVN_URL), null);
		Calendar date = Calendar.getInstance();
		repository.addSVNDirEntry(createDirEntry("/branches", "branchA", date.getTime()));
		repository.addSVNDirEntry(createDirEntry("/tags", "tagA", date.getTime()));
		date.roll(Calendar.YEAR, -1);
		repository.addSVNDirEntry(createDirEntry("/branches", "branchB", date.getTime()));
		repository.addSVNDirEntry(createDirEntry("/tags", "tagB", date.getTime()));
		PowerMockito.mockStatic(SVNRepositoryFactory.class);
		PowerMockito.doReturn(repository).when(SVNRepositoryFactory.class, "create", org.mockito.Matchers.any(SVNURL.class));
		scanner = new SVNScanner(SVN_URL, "username", "password");
	}

	private SVNDirEntry createDirEntry(String path, String name, Date date) throws SVNException {
		return new SVNDirEntry(SVNURL.parseURIEncoded(SVN_URL + path),
				SVNURL.parseURIEncoded(SVN_URL), name, SVNNodeKind.DIR, 4, false, 5, date, "user1");
	}

	@Test
	public void listBranches() throws Exception {
		Assert.assertEquals("[branchA, branchB]", scanner.listBranches().toString());
	}

	@Test
	public void listOldBranches() throws Exception {
		Calendar threeMonthsAgo = Calendar.getInstance();
		threeMonthsAgo.roll(Calendar.MONTH, -3);
		Assert.assertEquals("[branchB]", scanner.listBranches(threeMonthsAgo).toString());
	}

	@Test
	public void listOldTags() throws Exception {
		Calendar threeMonthsAgo = Calendar.getInstance();
		threeMonthsAgo.roll(Calendar.MONTH, -3);
		Assert.assertEquals("[tagB]", scanner.listTags(threeMonthsAgo).toString());
	}
}
