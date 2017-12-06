package com.winterchen;


import com.winterchen.domain.UserEntity;
import com.winterchen.domain.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartApplicationTests {

	private MockMvc mvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;





	@Before
	public void setUp() throws Exception{
		//userRepository.save(new UserEntity("AAA", 10));
	}

	@Test
	public void selectData() throws Exception {
		/*UserEntity u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询：" + u1.getAge());
		UserEntity u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询：" + u2.getAge());
		u2.setAge(55);
		userRepository.save(u2);

		UserEntity u3 = userRepository.findByName("AAA");
		System.out.println("第三次查询：" + u3.getAge());*/
	}

	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void addData() throws Exception{
		/*userRepository.save(new UserEntity("AAA", 10));
		userRepository.save(new UserEntity("BBB", 20));
		userRepository.save(new UserEntity("CCC", 30));
		userRepository.save(new UserEntity("EEE", 40));
		userRepository.save(new UserEntity("FFF", 50));
		userRepository.save(new UserEntity("GGG", 60));
		userRepository.save(new UserEntity("HHH", 70));
		userRepository.save(new UserEntity("III", 80));
		userRepository.save(new UserEntity("JJJ", 90));
		userRepository.save(new UserEntity("HHH", 100));

		Assert.assertEquals(10, userRepository.findAll().size());
		Assert.assertEquals(60, userRepository.findByName("GGG").getAge().longValue());
		Assert.assertEquals(60, userRepository.findUser("GGG").getAge().longValue());
		Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 50).getName());

		userRepository.delete(userRepository.findByName("AAA"));

		Assert.assertEquals(9, userRepository.findAll().size());*/



	}

	@Test
	public void sendSimpleMail() throws Exception{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1085143002@qq.com");
		message.setTo("1085143002@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}

	@Test
	public void sendAttachmentsMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1085143002@qq.com");
		helper.setTo("1193254946@qq.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");
		FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\图片\\九邑学士府.png"));
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);
		mailSender.send(mimeMessage);
	}

	@Test
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1085143002@qq.com");
		helper.setTo("1085143002@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);
		FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\图片\\九邑学士府.png"));
		helper.addInline("weixin", file);
		mailSender.send(mimeMessage);
	}

	@Test
	public void contextLoads() {
		UserEntity userEntity = userRepository.findByUserName("15258800630");
		System.out.println(userEntity);

	}



}
