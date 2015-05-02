package io.github.codemumbler.svn;

import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.io.*;

import java.io.OutputStream;
import java.util.*;

public class MockSVNRepository extends SVNRepository {

	private final List<SVNDirEntry> directories = new ArrayList<>();

	protected MockSVNRepository(SVNURL location, ISVNSession options) {
		super(location, options);
	}

	public void addSVNDirEntry(SVNDirEntry dirEntry) {
		directories.add(dirEntry);
	}

	@Override
	public void testConnection() throws SVNException {

	}

	@Override
	public long getLatestRevision() throws SVNException {
		return 0;
	}

	@Override
	public long getDatedRevision(Date date) throws SVNException {
		return 0;
	}

	@Override
	public SVNProperties getRevisionProperties(long l, SVNProperties svnProperties) throws SVNException {
		return null;
	}

	@Override
	public void setRevisionPropertyValue(long l, String s, SVNPropertyValue svnPropertyValue) throws SVNException {

	}

	@Override
	public SVNPropertyValue getRevisionPropertyValue(long l, String s) throws SVNException {
		return null;
	}

	@Override
	public SVNNodeKind checkPath(String s, long l) throws SVNException {
		return null;
	}

	@Override
	public long getFile(String s, long l, SVNProperties svnProperties, OutputStream outputStream) throws SVNException {
		return 0;
	}

	@Override
	public long getDir(String s, long l, SVNProperties svnProperties, ISVNDirEntryHandler isvnDirEntryHandler) throws SVNException {
		return 0;
	}

	@Override
	public long getDir(String path, long l, SVNProperties svnProperties, int i, ISVNDirEntryHandler handler) throws SVNException {
		for ( SVNDirEntry entry : directories) {
			if ( entry.getURL().getPath().equals(path) )
				handler.handleDirEntry(entry);
		}
		return 0;
	}

	@Override
	public SVNDirEntry getDir(String s, long l, boolean b, Collection collection) throws SVNException {
		return null;
	}

	@Override
	public void diff(SVNURL svnurl, long l, long l1, String s, boolean b, SVNDepth svnDepth, boolean b1, ISVNReporterBaton isvnReporterBaton, ISVNEditor isvnEditor) throws SVNException {

	}

	@Override
	public void update(SVNURL svnurl, long l, String s, SVNDepth svnDepth, ISVNReporterBaton isvnReporterBaton, ISVNEditor isvnEditor) throws SVNException {

	}

	@Override
	public void update(long l, String s, SVNDepth svnDepth, boolean b, ISVNReporterBaton isvnReporterBaton, ISVNEditor isvnEditor) throws SVNException {

	}

	@Override
	public void status(long l, String s, SVNDepth svnDepth, ISVNReporterBaton isvnReporterBaton, ISVNEditor isvnEditor) throws SVNException {

	}

	@Override
	public void replay(long l, long l1, boolean b, ISVNEditor isvnEditor) throws SVNException {

	}

	@Override
	public SVNDirEntry info(String s, long l) throws SVNException {
		return null;
	}

	@Override
	public ISVNEditor getCommitEditor(String s, Map map, boolean b, ISVNWorkspaceMediator isvnWorkspaceMediator) throws SVNException {
		return null;
	}

	@Override
	protected ISVNEditor getCommitEditorInternal(Map map, boolean b, SVNProperties svnProperties, ISVNWorkspaceMediator isvnWorkspaceMediator) throws SVNException {
		return null;
	}

	@Override
	public SVNLock getLock(String s) throws SVNException {
		return null;
	}

	@Override
	public SVNLock[] getLocks(String s) throws SVNException {
		return new SVNLock[0];
	}

	@Override
	public void lock(Map map, String s, boolean b, ISVNLockHandler isvnLockHandler) throws SVNException {

	}

	@Override
	public void unlock(Map map, boolean b, ISVNLockHandler isvnLockHandler) throws SVNException {

	}

	@Override
	public void closeSession() {

	}

	@Override
	public boolean hasCapability(SVNCapability svnCapability) throws SVNException {
		return false;
	}

	@Override
	protected void getInheritedPropertiesImpl(String s, long l, String s1, ISVNInheritedPropertiesHandler isvnInheritedPropertiesHandler) throws SVNException {

	}

	@Override
	protected long getDeletedRevisionImpl(String s, long l, long l1) throws SVNException {
		return 0;
	}

	@Override
	protected long getLocationSegmentsImpl(String s, long l, long l1, long l2, ISVNLocationSegmentHandler isvnLocationSegmentHandler) throws SVNException {
		return 0;
	}

	@Override
	protected int getLocationsImpl(String s, long l, long[] longs, ISVNLocationEntryHandler isvnLocationEntryHandler) throws SVNException {
		return 0;
	}

	@Override
	protected long logImpl(String[] strings, long l, long l1, boolean b, boolean b1, long l2, boolean b2, String[] strings1, ISVNLogEntryHandler isvnLogEntryHandler) throws SVNException {
		return 0;
	}

	@Override
	protected int getFileRevisionsImpl(String s, long l, long l1, boolean b, ISVNFileRevisionHandler isvnFileRevisionHandler) throws SVNException {
		return 0;
	}

	@Override
	protected Map getMergeInfoImpl(String[] strings, long l, SVNMergeInfoInheritance svnMergeInfoInheritance, boolean b) throws SVNException {
		return null;
	}

	@Override
	protected void replayRangeImpl(long l, long l1, long l2, boolean b, ISVNReplayHandler isvnReplayHandler) throws SVNException {

	}
}
