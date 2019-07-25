package skills.metrics.builders.projects

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import skills.services.AdminUsersService

@Component
@CompileStatic
class TopAchievedChartBuilder implements skills.metrics.builders.MetricsChartBuilder {

    final Integer displayOrder = 3

    @Autowired
    AdminUsersService adminUsersService

    @Override
    skills.metrics.model.Section getSection() {
        return skills.metrics.model.Section.projects
    }

    @Override
    skills.metrics.model.MetricsChart build(String projectId, Map<String, String> props, boolean loadData=true) {
        List<skills.controller.result.model.CountItem> dataItems = [] //(loadData ? adminUsersService.getUserCountsPerLevel(projectId) : []) as List<CountItem>

        skills.metrics.model.MetricsChart metricsChart = new skills.metrics.model.MetricsChart(
                chartType: skills.metrics.model.ChartType.VerticalBar,
                dataItems: dataItems,
                chartOptions: getChartOptions(),
        )
        return metricsChart
    }

    private Map<skills.metrics.model.ChartOption, Object> getChartOptions() {
        Map<skills.metrics.model.ChartOption, Object> chartOptions = [
                (skills.metrics.model.ChartOption.title)      : 'Top Achieved',
                (skills.metrics.model.ChartOption.subtitle)   : 'The Olympians of Skills',
                (skills.metrics.model.ChartOption.icon)       : 'fa fa-trophy',
                (skills.metrics.model.ChartOption.description): 'This stat depicts most achieved skills. It will display top skills and number of users that achieved those skills.',
                (skills.metrics.model.ChartOption.sort)       : 'asc',
        ] as Map<skills.metrics.model.ChartOption, Object>
        return chartOptions
    }
}