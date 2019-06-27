package com.meli.solr.api.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.solr.api.service.SolrSysService;

public class SolrSysInitializer {
	
		@Autowired
	    private SolrSysService solrSysService;

	    @PostConstruct
	    public void initialize() {
	    	solrSysService.init();
	    }

}

