package com.meli.solr.api.batch;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import io.github.jhipster.config.liquibase.AsyncSpringLiquibase;
import liquibase.exception.LiquibaseException;

public class AsyncSeedingSolrSys implements InitializingBean {

	private final Logger log = LoggerFactory.getLogger(AsyncSpringLiquibase.class);
	private final Executor executor;

	@Autowired
	private SolrSysBatch batch;

	public AsyncSeedingSolrSys(Executor executor) {
		this.executor = executor;
	}

	@Override
	public void afterPropertiesSet() {
		executor.execute(() -> {
			try {
				initDb();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});

	}


	protected void initDb() throws LiquibaseException {
		StopWatch watch = new StopWatch();
		watch.start();
		batch.init();
		watch.stop();
		if (watch.getTotalTimeMillis() > 45 * 1000L) {
			log.warn("lento", 45);
		}
	}


}
