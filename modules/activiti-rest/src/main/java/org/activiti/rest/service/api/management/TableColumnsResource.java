/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */

package org.activiti.rest.service.api.management;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.management.TableMetaData;
import org.activiti.rest.common.api.ActivitiUtil;
import org.activiti.rest.common.api.SecuredResource;
import org.restlet.resource.Get;

/**
 * @author Frederik Heremans
 */
public class TableColumnsResource extends SecuredResource {
  
  @Get
  public TableMetaData getTableMetaData() {
    if(authenticate() == false) return null;

    String tableName = getAttribute("tableName");
    if(tableName == null) {
      throw new ActivitiIllegalArgumentException("The tableName cannot be null");
    }
    
    TableMetaData response = ActivitiUtil.getManagementService().getTableMetaData(tableName);
   
   if(response == null) {
     throw new ActivitiObjectNotFoundException("Could not find a table with name '" + tableName + "'.", String.class);
   }
   return response;
  }
}
