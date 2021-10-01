package concerttours.jobs;

import concerttours.model.NewsModel;
import concerttours.service.NewsService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class SendNewsJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(SendNewsJob.class);
    private NewsService newsService;
    private ConfigurationService configurationService;

    public NewsService getNewsService() {
        return newsService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setNewsService(final NewsService newsService) {
        this.newsService = newsService;
    }

    public void setConfigurationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public PerformResult perform(final CronJobModel cronJob) {
        LOG.info("Sending news.");
        final List<NewsModel> newsItems = getNewsService().getNewsOfTheDay(new Date());
        if (newsItems.isEmpty()) {
            LOG.info("No news items for today.");
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }
        final StringBuilder content = new StringBuilder(2000);
        int index = 1;
        content.append("Todays news summary:\n\n");
        for (final NewsModel news : newsItems) {
            content.append(index++);
            content.append(". ");
            content.append(news.getHeadline());
            content.append("\n");
            content.append(news.getContent());
            content.append("\n\n");
        }

        LOG.info(content);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
