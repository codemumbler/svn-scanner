package io.github.codemumbler.svn;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.*;

public class SVNScanner {

	private static final String BRANCHES = "/branches";
	private static final String TAGS = "/tags";

	static {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}

	private SVNRepository repository;

	public SVNScanner(String url, String username, String password) throws SVNException {
		repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
		repository.setAuthenticationManager(authManager);
	}

	public List<String> listBranches() throws SVNException {
		return getDirectories(BRANCHES, Calendar.getInstance());
	}

	public List<String> listBranches(Calendar olderThan) throws SVNException {
		return getDirectories(BRANCHES, olderThan);
	}

	private List<String> getDirectories(String path, Calendar olderThan) throws SVNException {
		List<String> branches = new ArrayList<>();
		List<SVNDirEntry> entries = (List<SVNDirEntry>) repository.getDir(path, -1, null, (Collection) null);
		for (SVNDirEntry entry : entries) {
			if (olderThan.getTime().after(entry.getDate()))
				branches.add(entry.getName());
		}
		return branches;
	}

	public List<String> listTags(Calendar olderThan) throws SVNException {
		return getDirectories(TAGS, olderThan);
	}
}
