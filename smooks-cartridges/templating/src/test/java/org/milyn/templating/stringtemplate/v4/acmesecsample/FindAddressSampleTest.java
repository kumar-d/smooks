/*
	Milyn - Copyright (C) 2006 - 2010

	This library is free software; you can redistribute it and/or
	modify it under the terms of the GNU Lesser General Public
	License (version 2.1) as published by the Free Software 
	Foundation.

	This library is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
    
	See the GNU Lesser General Public License for more details:    
	http://www.gnu.org/licenses/lgpl.txt
*/

package org.milyn.templating.stringtemplate.v4.acmesecsample;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.SmooksUtil;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.StringResult;
import org.milyn.profile.DefaultProfileSet;
import org.milyn.templating.util.CharUtils;
import org.xml.sax.SAXException;

public class FindAddressSampleTest {

    @Test
    public void testTransform() throws SAXException, IOException {
        Smooks smooks = new Smooks();

        // Configure Smooks...
        SmooksUtil.registerProfileSet(DefaultProfileSet.create("acme-findAddresses-request", new String[] {"acme-request"}), smooks);
        smooks.addConfigurations("acme-creds.cdrl", getClass().getResourceAsStream("acme-creds.cdrl"));

        // Perform the transformation...
        InputStream requestStream = getClass().getResourceAsStream("AcmeFindaddressRequest.xml");
        ExecutionContext context = smooks.createExecutionContext("acme-findAddresses-request");
        String requestResult = SmooksUtil.filterAndSerialize(context, requestStream, smooks);
	CharUtils.assertEquals("StringTemplate test failed.", "/org/milyn/templating/stringtemplate/acmesecsample/AcmeFindaddressRequest.xml.tran.expected", requestResult);
    }
}
