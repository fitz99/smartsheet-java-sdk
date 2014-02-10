package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Folder;

public class WorkspaceFolderResourcesImplTest extends ResourcesImplBase {

	private WorkspaceFolderResourcesImpl workspaceFolderResources;

	@Before
	public void setUp() throws Exception {
		workspaceFolderResources = new WorkspaceFolderResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testWorkspaceFolderResourcesImpl() {}

	@Test
	public void testListFolders() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/listWorkspaceFolders.json"));
		
		List<Folder> folders = workspaceFolderResources.listFolders(1234L);
		assertEquals(1,folders.size());
		assertEquals(4298196408133508L, folders.get(0).getId().longValue());
		assertEquals("Human Resources", folders.get(0).getName());
		
	}

	@Test
	public void testCreateFolder() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/newWorkspaceFolder.json"));
		
		Folder folder = new Folder();
		folder.setName("New Folder");
		Folder newFolder = workspaceFolderResources.createFolder(1234L, folder);
		assertEquals(8121709439018884L, newFolder.getId().longValue());
		assertEquals("New Folder", newFolder.getName());
	}

}
