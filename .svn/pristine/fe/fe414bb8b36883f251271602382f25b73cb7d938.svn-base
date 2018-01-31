package org.work.solr;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.apache.solr.common.util.NamedList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * solr 本地测试
 * @author 11273
 *
 */
public class LocalGroupSolr {
	private static final String SOLR_URL = "http://192.168.0.120:8983/solr/";
	private static final String CROE_NAME = "oktest";
	
	public static void getOKGroup() throws SolrServerException, IOException {
		SolrClient client = new HttpSolrClient.Builder(SOLR_URL+CROE_NAME).build();
		SolrQuery query = new SolrQuery("*:*");
		//进行分组操作
		query.setParam(GroupParams.GROUP, true);
		query.setParam(GroupParams.GROUP_FIELD, "name");
		query.setParam(GroupParams.GROUP_SORT,"createTime asc");
		String[] strings = new String[] {"content:[4 TO 5]","createTime:[ * TO 2018-01-20T06:13:03Z ]"};
		query.setParam(GroupParams.GROUP_QUERY,strings);
		query.setParam(GroupParams.GROUP_LIMIT, "10");
		query.setParam(GroupParams.GROUP_OFFSET, "0");
		query.setParam(GroupParams.GROUP_MAIN, false);
		
//		query.setFields("name","content","id","createTime");
		//,"content:[4 TO 6]"
//		query.setFilterQueries("createTime:[* TO 2018-01-20T06:13:03Z]","content:[4 TO 5]");
//		query.setSort("createTime", SolrQuery.ORDER.asc);
		query.setStart(0);
		query.setRows(10);
		
		QueryResponse response = client.query(query);
		SolrDocumentList results2 = response.getResults();
		JSONArray result1 = new JSONArray();
        if (results2!=null){
            result1 = JSON.parseArray(JSONObject.toJSONString(results2));
            System.err.println(JSONObject.toJSONString(result1,true));
        }
		GroupResponse groupResponse = response.getGroupResponse();
		if (groupResponse==null) {
			return;
		}
		List<GroupCommand> values = groupResponse.getValues();
		for(GroupCommand groupCommand:values) {
			String name = groupCommand.getName();
			if ("name".equals(name)) {
				List<Group> values2 = groupCommand.getValues();
				for(Group group : values2) {
					JSONObject result = JSONObject.parseObject(JSON.toJSONString(group.getResult().get(0)));
					Date date =  result.getDate("createTime");
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:ss:mm");
					String dateStr = simpleDateFormat.format(date);
					System.err.println(dateStr);
					System.err.println(JSONObject.toJSONString(result,true));
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) throws SolrServerException, IOException {
		getOKGroup();
	}

}













