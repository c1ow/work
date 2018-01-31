package org.work.solr;

import java.io.IOException;
import java.text.ParseException;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author 11273
 * solr 分页测试
 * SolrJ
 */
public class SolrGroup {
	private final String SOLR_URL="solr2-sjzx.aviptcare.com";
	private final String CORE_NAME="xzhisdata";
	private static final String ID_NOS = "( idNo:142729199509159589 OR idNo:320602199409053791 OR idNo:440201198307144865 "
			+ "OR idNo:522622198805310091 OR idNo:650101197604223833 OR idNo:142729199509156302 OR idNo:542323198206128670 "
			+ "OR idNo:360481197309193159 OR idNo:650101199004151716 OR idNo:222402198702109443 OR idNo:320502197812092024"
			+ " OR idNo:142729199509150488 OR idNo:141102197607185825 OR idNo:21022419830914502X OR idNo:440206198606225307 "
			+ "OR idNo:610111199411097302 OR idNo:120222198601015359 OR idNo:42098119900731355X OR idNo:141102198908198042 "
			+ "OR idNo:430101198504112398 OR idNo:451021199207131911 OR idNo:542323197003234858 OR idNo:231085197509205447"
			+ " OR idNo:500105197701186600 OR idNo:500242198201257208 OR idNo:542323197908189879 OR idNo:360481197303135396 "
			+ "OR idNo:150204199201016499 OR idNo:360481197309107935 OR idNo:210403198910045213 OR idNo:22240219890816136X "
			+ "OR idNo:542323197505264379 OR idNo:430101197709187137 OR idNo:430101198903142279 OR idNo:653024197412039566 "
			+ "OR idNo:220523198909261819 OR idNo:231085197904194549 OR idNo:330782197611033639 OR idNo:210224199007216341 "
			+ "OR idNo:210224199004201646 "
			+ "OR idNo:210224197603133885 OR idNo:430101197802104391 OR idNo:341424197107132243 OR idNo:542323197704208513 "
			+ "OR idNo:650101197905253833 OR idNo:542500197304211349 OR idNo:37148219830115145X OR idNo:220523198106225531 "
			+ "OR idNo:15040319501030470X OR idNo:21022419860219500X OR idNo:542500198101109226 OR idNo:440201197804249647 "
			+ "OR idNo:451229197801253762 OR idNo:130406199501014516 OR idNo:411527197409117973 OR idNo:542500198707108527 "
			+ "OR idNo:220523197804173535 OR idNo:542500197503171781 OR idNo:430101198104236199 OR idNo:500112197907135538 "
			+ "OR idNo:650101199003253913 OR idNo:430101198302165213 OR idNo:440201198903234920 OR idNo:231085198901271726 "
			+ "OR idNo:330303198302263737)";
	
	
	/**
	 * solr 搜索
	 * @param sortIndex
	 * @param pageNo
	 * @param pageSize
	 * @param filterQueries
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public JSONObject getQueryResult(String sortIndex, Integer pageNo, Integer pageSize,
			String... filterQueries) throws IOException, SolrServerException {
        //获取solr核心(配置数据库中)

        //根据条件查询数据
        //创建solr实例（）

        if (pageNo==null||pageNo==0){
            pageNo = 1;
        }

        if (pageSize==null||pageSize==0){
            pageSize=20;
        }

        SolrClient solrClient = new HttpSolrClient.Builder("http://"+SOLR_URL+"/solr/"+CORE_NAME).build();
        SolrQuery solrQuery = new SolrQuery("*:*");
        if (pageNo!=null&&pageSize!=null){
            solrQuery.setStart((pageNo-1)*pageSize);
            solrQuery.setRows(pageSize);
        }
        if (sortIndex!=null&&!"".equals(sortIndex)){
            solrQuery.setSort(sortIndex, SolrQuery.ORDER.desc);
        }
        solrQuery.setFilterQueries(filterQueries);

        QueryResponse queryResponse = solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        JSONArray result = new JSONArray();
        if (solrDocumentList!=null){
            result = JSON.parseArray(JSONObject.toJSONString(solrDocumentList));
        }
        //获取总条数
        Long countRes = solrDocumentList.getNumFound();
        int count = 0;
        if (countRes != null ){
            count = Math.toIntExact(countRes);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

	/**
	 * solrj 分组查询（dome）
	 * @param sortIndex
	 * @param pageNo
	 * @param pageSize
	 * @param filterQueries
	 * @return
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public JSONObject getGroupSolr(String sortIndex, Integer pageNo, Integer pageSize,
			String... filterQueries) throws SolrServerException, IOException {
		//实例化客户端
		SolrClient solrClient = new HttpSolrClient.Builder("http://"+SOLR_URL+"/solr/"+CORE_NAME).build();
		//添加索引
		SolrQuery solrQuery = new SolrQuery("*:*");
		solrQuery.setFilterQueries(filterQueries);
		solrQuery.setSort(sortIndex,SolrQuery.ORDER.desc);
		solrQuery.setParam(GroupParams.GROUP, true);
		solrQuery.setParam(GroupParams.GROUP_FIELD, "userName");
		solrQuery.setParam(GroupParams.GROUP_LIMIT, "1");
		solrQuery.setParam(GroupParams.GROUP_OFFSET, "0");
		String[] fields = new String[] {"idNo","units","answer","resultTime","assayItemId"};
		solrQuery.setFields(fields);
		//,"answer:{0.0 TO 130.0]"numIndex:{ 450.0 TO 7000.0 ]
		String[] ss = new String[] {ID_NOS};
		solrQuery.setParam(GroupParams.GROUP_QUERY,ss);
		solrQuery.set("df", "id");
//		solrQuery.set("facet", "on");
//		solrQuery.set("facet.field","idNo");
//		solrQuery.set("facet.mincount", "1");
//		solrQuery.set("facet.limit", "15");
//		solrQuery.setParam(GroupParams.GROUP_OFFSET, "1");
//		solrQuery.setParam(GroupParams.GROUP_SORT, "resultTime desc");
		
		
		solrQuery.setStart(pageNo);
		solrQuery.setRows(pageSize);
		//执行查询
		QueryResponse queryResponse = solrClient.query(solrQuery);
		
		SolrDocumentList solrDocumentList = queryResponse.getResults();
        JSONArray result = new JSONArray();
        if (solrDocumentList!=null){
            result = JSON.parseArray(JSONObject.toJSONString(solrDocumentList));
            System.err.println(JSONObject.toJSONString(result,true));
        }
		
		//结果抽取
		GroupResponse groupResponse = queryResponse.getGroupResponse();
		List<GroupCommand> values = groupResponse.getValues();
		for(GroupCommand groupCommand : values) {
//			System.err.println("获取的数据"+JSONObject.toJSONString(groupCommand,true));
			String name = groupCommand.getName();
			if ("userName".equals(name)) {
				List<Group> values2 = groupCommand.getValues();
				for(Group group:values2) {
					SolrDocumentList result2 = group.getResult();
					System.err.println(JSONObject.toJSONString(group.getResult(),true));
				}
			}
		}
		return new JSONObject();
	}
	
	public static String UTC8(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateString);
        //28800000L 减去8小时
        Long dateTime = date.getTime() - 28800000L;
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd&1HH:mm:ss&2");
        String res = simpleDateFormat1.format(dateTime);
        res = res.replaceAll("&1", "T");
        res = res.replaceAll("&2", "Z");
        System.err.println(res);
        return res;
    }
	
	//主方法
	public static void main(String[] args) throws IOException, SolrServerException, ParseException {
		String[] filterQueries = new String[]{"tableType:10410061000000002", "itemCode:10010630010000037"};
		SolrGroup solrGroup = new SolrGroup();
//		JSONObject jsonObject = solrGroup.getQueryResult(null, 1, 20, filterQueries);
//		System.err.println("数据："+JSONObject.toJSONString(jsonObject,true));
		String resultTime = "resultTime:[ "+UTC8("2016-12-06")+" TO "+UTC8("2017-12-07") +" ]";
		System.err.println(resultTime);
		String[] filterQueries1 = new String[]{ID_NOS,"tableType:10410061000000002","itemCode:10010630010000037",resultTime,"numIndex:{ 0.0 TO 50.0 ]"};
		JSONObject groupSolr = solrGroup.getGroupSolr("resultTime", 0,20, filterQueries1);
		System.err.println("分组数据："+JSONObject.toJSONString(groupSolr));
		Long x = 1000000L;
//		String ls = "lll@wo";
//		String[] split = ls.split("@");
//		String ss = split[0];
//		System.err.println("=="+ss);
		
		
	}
}
