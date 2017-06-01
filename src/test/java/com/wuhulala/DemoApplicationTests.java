package com.wuhulala;

import com.alibaba.fastjson.JSON;
import com.wuhulala.dal.mapper.QaMapper;
import com.wuhulala.dal.model.Qa;
import com.wuhulala.model.Page;
import com.wuhulala.service.QaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	QaMapper qaMapper;

	@Autowired
	QaService qaService;
	@Test
	public void contextLoads() {
		System.out.println(JSON.toJSONString(qaMapper.findAll()));
		/*Long[] idd= {1L,2L,3L};
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		System.out.println(JSON.toJSONString(qaMapper.findByIdList(ids)));*/
	}

	@Test
	public void testAdd(){



		long start = System.currentTimeMillis();
		for(int i = 0 ; i < 100 ; i++){
			List<Qa> qas = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			/*for (int k = 0; k < 10000; k++) {
				sb.append("a");
			}*/
			for (int j = 0; j <500 ; j++) {
				int key =  i * 500 + j;
				Qa qa2 = new Qa();
				qa2.setAuthorId(1L);

				qa2.setTitle("qa_" + key);
				qa2.setContent(sb.append("Qa_title_").append(key).toString());
				qas.add(qa2);
			}
			qaMapper.insertList(qas);

		}
		long end1 = System.currentTimeMillis();

		//qaMapper.insertList(qas);

		long end2 = System.currentTimeMillis();

		System.out.println("single cost time:[" + (end1 - start) + "ms]");
		//System.out.println("batch cost time:[" + (end2 - end1) + "ms]");

		//single cost time:[324024ms]
		//100 batch cost time:[6958ms]
		//500 batch cost time[2560ms]
		//10000 batch cost time:[1096ms]

		//并且mysql默认的数据包大小为4M 可以修改C:\ProgramData\MySQL\MySQL Server 5.7（具体路径为你配置服务的时候配置的路径）目录下的my.ini参数max_allowed_packet，
		//数据包过大报错exception com.mysql.jdbc.PacketTooBigException: Packet for query is too large (51494488 > 4194304)
		//但是参数配置过大，不容易出现错误，但是网络传输时间与mysql服务器处理时间和占用资源都是需要考虑的，很可能报超时，我这本地i7+16G的笔记本，在第50+条的时候，也超时了
	}

	@Test
	public void testPage(){
		Page<Qa> page = new Page<>();
		Qa qa = new Qa();
		qa.setTitle("asdasd");
		page.setItem(qa);
		page.setKeyword("asdasd");
		page.setLength(10);
		page.setStart(1);
		System.out.println(JSON.toJSONString(qaService.findWithPage(page)));
	}

}
