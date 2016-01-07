/**
 * Copyright (c) 2009 Pyxis Technologies inc.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF site:
 * http://www.fsf.org.
 */
package jenkins.plugins.livingdoc;

import hudson.model.HealthReport;
import hudson.model.HealthReportingAction;
import hudson.model.AbstractBuild;
import jenkins.plugins.livingdoc.results.BuildSummaryResult;

import org.kohsuke.stapler.StaplerProxy;


public class LivingDocBuildAction implements HealthReportingAction, StaplerProxy {

    private final AbstractBuild< ? , ? > build;
    private final SummaryBuildReportBean summary;

    public LivingDocBuildAction (AbstractBuild< ? , ? > build, SummaryBuildReportBean summary) {
        this.build = build;
        this.summary = summary;
    }

    public SummaryBuildReportBean getSummary () {
        return summary;
    }

    public HealthReport getBuildHealth () {

        BuildSummaryResult result = getResult();

        return new HealthReport(result.getSuccessRate(), Messages._LivingDocBuildAction_description(result.getStatistics()));
    }

    public String getIconFileName () {
        return ResourceUtils.BIG_ICON_URL;
    }

    public String getUrlName () {
        return ResourceUtils.PLUGIN_CONTEXT_BASE;
    }

    public String getDisplayName () {
        return "testIT LivingDoc";
    }

    public Object getTarget () {
        return getResult();
    }

    private BuildSummaryResult getResult () {
        return new BuildSummaryResult(build, summary);
    }
}