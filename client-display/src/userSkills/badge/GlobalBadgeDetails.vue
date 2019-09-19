<template>
    <div class="container">
        <skills-spinner :loading="loading" />

        <div v-if="!loading">
            <skills-title>Global Badge Details</skills-title>

            <div class="card">
                <div class="card-body">
                    <badge-details-overview :badge="badge"></badge-details-overview>
                </div>
            </div>

            <div v-for="projectSummary in projectSummaries" :key="projectSummary.projectId" class="card mt-1">
                <h4 class="card-header text-sm-left text-secondary text-center col">Project: {{ projectSummary.projectName }}</h4>
                <div class="card-body">
                    <project-level-row v-if="projectSummary && projectSummary.projectLevel" :projectLevel="projectSummary.projectLevel" />
                    <skills-progress-list v-if="projectSummary && projectSummary.skills" :subject="projectSummary" :show-descriptions="showDescriptions" :helpTipHref="helpTipHref" type="badge"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import BadgeDetailsOverview from '@/userSkills/badge/BadgeDetailsOverview.vue';
    import SkillsProgressList from '@/userSkills/skill/progress/SkillsProgressList.vue';
    import ProjectLevelRow from '@/userSkills/badge/ProjectLevelRow.vue';
    import SkillsSpinner from '@/common/utilities/SkillsSpinner.vue';

    import UserSkillsService from '@/userSkills/service/UserSkillsService';

    import SkillsTitle from '@/common/utilities/SkillsTitle.vue';

    export default {
        components: {
            SkillsTitle,
            SkillsProgressList,
            BadgeDetailsOverview,
            SkillsSpinner,
            ProjectLevelRow,
        },
        data() {
            return {
                loading: true,
                badge: null,
                initialized: false,
                showDescriptions: false,
            };
        },
        computed: {
            helpTipHref() {
                return 'http://url';
            },
            projectSummaries() {
                return this.badge.projectLevelsAndSkillsSummaries.map(item => ({
                    badgeId: this.badge.badgeId,
                    projectId: item.projectId,
                    projectName: item.projectName,
                    skills: item.skills,
                    projectLevel: item.projectLevel,
                  }));
            },
        },
        watch: {
            $route: 'fetchData',
        },
        mounted() {
            this.fetchData();
        },
        methods: {
            fetchData() {
              UserSkillsService.getBadgeSkills(this.$route.params.badgeId, true)
                .then((badgeSummary) => {
                  this.badge = badgeSummary;
                  this.loading = false;
                });
            },
        },
    };
</script>

<style scoped>

</style>