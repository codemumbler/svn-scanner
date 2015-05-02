package io.github.codemumbler;

import org.antlr.runtime.*;
import org.antlr.runtime.BitSet;
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

	static {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}

	private SVNRepository repository;

	public SVNScanner(String url, String username, String password) {
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		} catch (SVNException e) {
			e.printStackTrace();
		}
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
		repository.setAuthenticationManager(authManager);
	}

	public List<String> listBranches() throws SVNException {
		return getDirectories("/branches", Calendar.getInstance());
	}

	public List<String> listBranches(Calendar olderThan) throws SVNException {
		return getDirectories("/branches", olderThan);
	}

	private List<String> getDirectories(String path, Calendar olderThan) throws SVNException {
		List<String> branches = new ArrayList<String>();
		Collection entries = repository.getDir(path, -1, null, (Collection) null);
		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();
			if ( olderThan.getTime().after(entry.getDate()) )
				branches.add(entry.getName());
		}
		return branches;
	}

	public List<String> listTags(Calendar olderThan) throws SVNException {
		return getDirectories("/tags", olderThan);
	}
}
