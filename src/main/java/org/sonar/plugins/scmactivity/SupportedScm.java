/*
 * SonarQube SCM Activity Plugin
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.scmactivity;

import org.apache.maven.scm.provider.ScmProvider;
import org.apache.maven.scm.provider.accurev.AccuRevScmProvider;
import org.apache.maven.scm.provider.bazaar.BazaarScmProvider;
import org.apache.maven.scm.provider.clearcase.ClearCaseScmProvider;
import org.apache.maven.scm.provider.cvslib.cvsexe.CvsExeScmProvider;
import org.apache.maven.scm.provider.hg.HgScmProvider;
import org.apache.maven.scm.provider.integrity.IntegrityScmProvider;
import org.apache.maven.scm.provider.jazz.JazzScmProvider;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.sonar.plugins.scmactivity.maven.SonarGitExeScmProvider;
import org.sonar.plugins.scmactivity.maven.SonarSvnExeScmProvider;
import org.sonar.plugins.scmactivity.maven.SonarTfsScmProvider;

public enum SupportedScm {
  SVN(new SonarSvnExeScmProvider(), "scm:svn:svn://"),
  CVS(new CvsExeScmProvider(), null),
  GIT(new SonarGitExeScmProvider(), "scm:git:"),
  HG(new HgScmProvider(), "scm:hg:"),
  BAZAAR(new BazaarScmProvider(), "scm:bazaar:"),
  CLEAR_CASE(new ClearCaseScmProvider(), null),
  ACCU_REV(new AccuRevScmProvider(), null),
  PERFORCE(new PerforceScmProvider(), null),
  TFS(new SonarTfsScmProvider(), "scm:tfs:"),
  JAZZ(new JazzScmProvider(), null),
  INTEGRITY(new IntegrityScmProvider(), null);

  private final ScmProvider provider;
  private final String guessedUrl;

  private SupportedScm(ScmProvider provider, String guessedUrl) {
    this.provider = provider;
    this.guessedUrl = guessedUrl;
  }

  public String getGuessedUrl() {
    return guessedUrl;
  }

  public String getType() {
    return provider.getScmType();
  }

  public String getScmSpecificFilename() {
    return provider.getScmSpecificFilename();
  }

  public ScmProvider getProvider() {
    return provider;
  }

}
