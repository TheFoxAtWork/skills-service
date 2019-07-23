package skills.metrics.builders.projects

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import skills.services.AdminUsersService

@Component
@CompileStatic
class ContinuedUsageChartBuilder implements skills.metrics.builders.MetricsChartBuilder {

    final Integer displayOrder = 6

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
                (skills.metrics.model.ChartOption.title)      : 'Continued Usage',
                (skills.metrics.model.ChartOption.subtitle)   : 'Do they stick around?',
                (skills.metrics.model.ChartOption.icon)       : 'fa fa-suitcase',
                (skills.metrics.model.ChartOption.description): 'Once a particular skill is achieve, do users continue using that skill?',
                (skills.metrics.model.ChartOption.sort)       : 'asc',
        ] as Map<skills.metrics.model.ChartOption, Object>
        return chartOptions
    }
}
