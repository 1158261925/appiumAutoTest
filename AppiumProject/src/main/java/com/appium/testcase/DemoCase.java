package com.appium.testcase;

import com.appium.init.InitDriver;
import com.appium.utils.AppiumUtils;
import com.appium.utils.GetByLocation;
import com.appium.utils.JdbcUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class DemoCase {

	private static Logger log = LoggerFactory.getLogger(DemoCase.class);
	
	//声明局部变量driver
	private AppiumDriver<MobileElement> driver;

	private QueryRunner qr = new QueryRunner();

	//构造方法初始化driver
	public DemoCase(AppiumDriver<MobileElement> driver) {
		
		this.driver = driver;
	}

	/**
	 * 登录脚本
	 * @throws Exception
	 */
	public void login() throws Exception {

		//声明自定义工具类GetByLocation对象，用于读取数据分离文件中的元素定位信息
		GetByLocation getByLocation = new GetByLocation("login.properties");

		Set<String> sets = driver.getContextHandles();
		for(String context : sets){
			System.out.println("-------------->context遍历："+context);
		}
		getByLocation.getElementObj("mBtAgree",driver).click();
		log.info("弹出用户注册及服务协议、隐私政策，点击我同意");
		Thread.sleep(3000);
		if(driver.getPageSource().contains("马上拿钱")){
			log.info("登录前初始化进入首页成功");
		}else{
			log.error("登录前初始化进入首页失败！");
		}

		getByLocation.getElementObj("mBtNext",driver).click();
		getByLocation.getElementObj("mIvPhoneEmpty",driver).click();
		getByLocation.getElementObj("mEtPhoneNum",driver).sendKeys("13051781111");
		getByLocation.getElementObj("nextStep",driver).click();
		getByLocation.getElementObj("aggreeCheck",driver).click();
		getByLocation.getElementObj("sendMsg",driver).sendKeys("123456");
		getByLocation.getElementObj("loginButton",driver).click();
		Thread.sleep(3000);
		if (driver.getPageSource().contains("个人敏感信息授权协议")){
			log.info("登录成功弹出《个人敏感信息授权协议》");
			getByLocation.getElementObj("mBtAgree4logSuccess",driver).click();
		}
		Thread.sleep(3000);
		if (driver.getPageSource().contains("有问题找客服：")){
			log.info("登录任意花app成功");
		}else{
			log.error("登录任意花app失败");
		}

	}

	/**
	 * 实名认证及提交授信风控流程
	 * @throws Exception
	 */
	public void idoAuthAndSupplementInfo() throws Exception {
		//声明自定义工具类GetByLocation对象，用于读取数据分离文件中的元素定位信息
		GetByLocation getByLocation = new GetByLocation("idoAuthAndRisk.properties");
		getByLocation.getElementObj("toBorrow",driver).click();
		log.info("进入实名认证页面");
		getByLocation.getElementObj("idoNameInput",driver).sendKeys("王晓");
		getByLocation.getElementObj("idoNumInput",driver).sendKeys("1101011990098888888");
		getByLocation.getElementObj("getFront",driver).click();
		getByLocation.getElementObj("getFromPicLib",driver).click();
		Thread.sleep(3000);
		TouchAction touch = new TouchAction(driver);
		touch.tap(PointOption.point(651,1134)).release().perform();
		Thread.sleep(3000);
		touch.tap(PointOption.point(651,1360)).release().perform();
		log.info("选择身份证正面成功！");
		getByLocation.getElementObj("getBack",driver).click();
		getByLocation.getElementObj("getFromPicLib2",driver).click();
		Thread.sleep(3000);
		touch.tap(PointOption.point(651,1134)).release().perform();
		Thread.sleep(3000);
		touch.tap(PointOption.point(651,894)).release().perform();
		log.info("选择身份证背面成功！");
		getByLocation.getElementObj("idoSubmit",driver).click();
		log.info("提交实名认证申请");
		WebDriverWait wait=new WebDriverWait(driver, 20);
		AndroidElement ele1=(AndroidElement)wait.until(ExpectedConditions.presenceOfElementLocated(getByLocation.getLocatorApp("waitForIdoSubmit")));
		log.info("进入资质评估页前弹出《个人敏感信息授权协议》");
		getByLocation.getElementObj("agreeButtonThree",driver).click();
		log.info("进入资质评估页");
		getByLocation.getElementObj("baseInfo",driver).click();
		log.info("进入授信前基本信息页面");
		Thread.sleep(4000);
		getByLocation.getElementObj("homeArea",driver).click();
		getByLocation.getElementObj("confirmButton",driver).click();
		log.info("选择的居住地址为北京市东城区");

		getByLocation.getElementObj("homeAddress",driver).sendKeys("北京市朝阳区XXXX小区2号楼3单元");

		getByLocation.getElementObj("educationInfo",driver).click();
		getByLocation.getElementObj("confirmButton",driver).click();
		log.info("选择的学历为博士");
		getByLocation.getElementObj("jobInfo",driver).click();
		getByLocation.getElementObj("confirmButton",driver).click();
		log.info("选择的职业为个体");
		getByLocation.getElementObj("monthIncome",driver).click();
		getByLocation.getElementObj("confirmButton",driver).click();
		log.info("选择的月收入为50000元以上");
		getByLocation.getElementObj("mTvEmail",driver).click();
		getByLocation.getElementObj("mEtEmail",driver).sendKeys("1158261925");
		driver.findElementsById("com.renmai.easymoney:id/mTvEmail").get(0).click();
		getByLocation.getElementObj("submitEmail",driver).click();
		log.info("填写的邮箱为：1158261925@qq.com，并提交");
		getByLocation.getElementObj("bankHint",driver).click();
		log.info("点击绑定银行卡按钮");
		getByLocation.getElementObj("bankUserName",driver).sendKeys("王晓曈");
		Random ran = new Random();
		int ranNum = ran.nextInt(9999-1000+1)+1000;
		String bankNo = "622622010332"+ranNum;
		getByLocation.getElementObj("bankNumber",driver).sendKeys(bankNo);
		getByLocation.getElementObj("bankPhoneNum",driver).sendKeys("13051781278");
		getByLocation.getElementObj("sendBankmsgButton",driver).click();
		getByLocation.getElementObj("bankMsgCode",driver).sendKeys("111111");
		getByLocation.getElementObj("checkBankProto",driver).click();
		log.info("提交新绑定的银行卡，银行卡号为：{}",bankNo);
		getByLocation.getElementObj("confirmBankButton",driver).click();
		Thread.sleep(4000);
		if (driver.getPageSource().contains("个人敏感信息授权协议")){
			log.info("授信前补充资料提交后弹出《个人敏感信息授权协议》");
			getByLocation.getElementObj("confirmForPri",driver).click();
		}

		getByLocation.getElementObj("submit4Risk",driver).click();
		Thread.sleep(3000);
		if (driver.getPageSource().contains("资料审核中，请稍等片刻请不要离开")){
			log.info("资料审核中，请稍等片刻请不要离开〜");
		}else {
			log.error("提交授信风控出现异常");
		}

		WebDriverWait waitForPass=new WebDriverWait(driver, 300);
		AndroidElement withdrawButton=(AndroidElement)waitForPass.until(ExpectedConditions.presenceOfElementLocated(getByLocation.getLocatorApp("submitCrash")));
		log.info("授信风控通过！");
	}
	/**
	 * 提现流程
	 * @throws Exception
	 */
	public void withdrawApply() throws Exception {
		GetByLocation getByLocation = new GetByLocation("withdrawApply.properties");
		getByLocation.getElementObj("goCrash",driver).click();
		Thread.sleep(5000);

		//切换context
		AppiumUtils.setContextToWebview(driver);
		log.info("提现申请页面切换context：{}",driver.getContext());
		if (driver.getPageSource().contains("哈尔滨市元丰小额贷款股份有限公司为持牌金融机构")){
			log.info("进入提现页面成功啦!");
		}else{
			log.error("进入提现页面失败了!");
		}

		/**
		 * <ul data-v-26d2ffdd="" class="periods">
		 * 	<li data-v-26d2ffdd="" class="choosePeriod">借3个月</li>
		 * 	<li data-v-26d2ffdd="" class="">借6个月</li>
		 * 	<li data-v-26d2ffdd="" class="">借12个月</li>
		 * </ul>
		 *
		 * 以上是web视图HTML代码，通过xpath定位元素，标签节点如ul、li等类似于uiautomatorviewer定位原生元素中class字段，写在//后面；
		 * 标签中的属性如class、id等写在[@class='xxx']、[@id='xxx']中；getText()方法可以获取元素的text值
		 * 这些与安卓原生元素xpath定位稍有区别，自己慢慢体会
		 */
		log.info("提现申请页面默认显示的期数是：{}",getByLocation.getElementObj("defaultPeroid",driver).getText());
		getByLocation.getElementObj("editAmount",driver).click();
		getByLocation.getElementObj("amountInput",driver).clear();
		getByLocation.getElementObj("amountInput",driver).sendKeys("3000");
		for(int i =0;i<2;i++){
			getByLocation.getElementObj("choosePeroid",driver).click();
			Thread.sleep(3000);
		}
		log.info("选择的期数为：{}",getByLocation.getElementObj("choosePeroid",driver).getText());

		log.info("综合年化成本为：{}",getByLocation.getElementObj("chengben",driver).getText());

		//点击每期应还查看详情
		getByLocation.getElementObj("shouldRepayNum",driver).click();

		log.info("每期应还：{}",getByLocation.getElementObj("shouldRepayText",driver).getText());

		driver.context("NATIVE_APP");

		log.info("切换后的context是：{}",driver.getContext());

		AppiumUtils.swipeToUp(driver);

		//切换到webview的context
		AppiumUtils.setContextToWebview(driver);

		log.info("再次切换congtext为：{}",driver.getContext());

		getByLocation.getElementObj("purpose",driver).click();

		Thread.sleep(3000);

		getByLocation.getElementObj("purposeBtnConfirm2",driver).click();

		Thread.sleep(3000);

		//点击个人信息补充
		getByLocation.getElementObj("personInfoImplement",driver).click();

		Thread.sleep(5000);
		//点击民族按钮
		getByLocation.getElementObj("minzu",driver).click();

		Thread.sleep(3000);

		try {
			getByLocation.getElementObj("minzuBtnConfirm1",driver).click();
			log.info("try里面的方法执行成功");
		}catch (Exception e){
			getByLocation.getElementObj("minzuBtnConfirm2",driver).click();
			log.info("catch里面的民族点击确认执行成功------>/html/body/div[5]/div/div/div[1]/div/a/span");
		}

		Thread.sleep(3000);

		getByLocation.getElementObj("companyName",driver).sendKeys("某某某某股份有限公司");

		Thread.sleep(3000);

		getByLocation.getElementObj("hourseAttribute",driver).click();

		Thread.sleep(3000);

		getByLocation.getElementObj("hourseBtnConfirm",driver).click();

		Thread.sleep(3000);

		getByLocation.getElementObj("mariageStatus",driver).click();

		Thread.sleep(3000);

		getByLocation.getElementObj("mariageBtnConfirm",driver).click();

		getByLocation.getElementObj("income4Withdraw",driver).sendKeys("8000");

		Thread.sleep(3000);

		getByLocation.getElementObj("supplementBtn",driver).click();

		log.info("提交个人信息补充资料成功，返回到提现申请页面");

		Thread.sleep(3000);

		//点击联系人信息补充按钮，进入联系人页面
		getByLocation.getElementObj("contactBtn",driver).click();

		Thread.sleep(3000);
		//点击"与本人关系"的请选择箭头
		getByLocation.getElementObj("relationMe",driver).click();

		Thread.sleep(3000);
		//点击"完成"按钮
		getByLocation.getElementObj("aboutMeBtn",driver).click();

		Thread.sleep(4000);

		driver.context("NATIVE_APP");

		log.info("紧急联系人页面滑动前的context为{}",driver.getContext());

		AppiumUtils.swipeToUp(driver);

		AppiumUtils.setContextToWebview(driver);

		log.info("向上滑动紧急联系人页面后切换context为：{}",driver.getContext());

		getByLocation.getElementObj("iAgreeProto",driver).click();

		log.info("勾选--->我已阅读，并同意将上述所列电话号码为本人逾期后的紧急联系人号码，并告知联系人");

		getByLocation.getElementObj("contactpageBtn",driver).click();

		log.info("点击紧急联系人提交按钮");

		Thread.sleep(4000);

		driver.context("NATIVE_APP");

		AppiumUtils.swipeToUp(driver);

		AppiumUtils.setContextToWebview(driver);

		log.info("向上滑动提现申请页面后切换context为：{}",driver.getContext());
		//提现页面点击绑定银行卡按钮
		getByLocation.getElementObj("bankBindBtn",driver).click();

		Thread.sleep(3000);
		//点击添加银行卡
		getByLocation.getElementObj("addBankCardText",driver).click();

		Thread.sleep(3000);

		//随机生成银行卡后四位，拼接银行卡号
		Random ran = new Random();
		int ranNum = ran.nextInt(9999-1000+1)+1000;
		String bankNo = "622622010336"+ranNum;
		//输入银行卡号
		getByLocation.getElementObj("inputBandNum",driver).sendKeys(bankNo);

		log.info("提现银行卡号为：{}",bankNo);

		Thread.sleep(2000);

		getByLocation.getElementObj("BankBelong",driver).click();

		Thread.sleep(3000);

		//点击确认《委托划扣授权书》
		getByLocation.getElementObj("agreeBankHK",driver).click();

		log.info("已点击确认同意委托划扣授权书");

		//点击获取短信验证码
		getByLocation.getElementObj("getMsgMobie",driver).click();

		Thread.sleep(3000);

		//绑卡页面输入短验
		getByLocation.getElementObj("msgcode4Bank",driver).sendKeys("111111");
		//点击确认添加银行卡按钮
		getByLocation.getElementObj("submitBankBtn",driver).click();

		Thread.sleep(4000);

		driver.context("NATIVE_APP");

		AppiumUtils.swipeToUp(driver);

		AppiumUtils.setContextToWebview(driver);
		//点击去提现按钮
		getByLocation.getElementObj("applyCrashBtn",driver).click();

		Thread.sleep(3000);

		driver.context("NATIVE_APP");

		AppiumUtils.swipeToUp(driver);

		AppiumUtils.setContextToWebview(driver);

		//勾选我同意协议按钮
		getByLocation.getElementObj("withdrawAgreeBtn",driver).click();

		log.info("点击本人已阅读并同意签署：{}",getByLocation.getElementObj("protocalText",driver).getText());

		//点击同意协议并继续提现
		getByLocation.getElementObj("confirmToCrashBtn",driver).click();

		Thread.sleep(2000);

		//从数据库中获取6位短信验证码
		Map<String,Object> queryResult = qr.query(JdbcUtil.getConnection(),"select i.user_id,i.real_name,w.msg_content,w.send_time from apply_idno_info i LEFT JOIN withdraw_send_msg_info w on i.user_id=w.user_id where real_name='王晓曈' order by w.send_time desc limit 1",new MapHandler());

		log.info("查询的sql语句为：{}",queryResult.toString());
		//将数据库中的短信验证码转化成字符串格式
		String msgCode = queryResult.get("msg_content").toString();
		//将短信验证码转成char[]数组
		char[] charMsg = msgCode.toCharArray();

		log.info("数据库中查询到的短验为：{}",msgCode);

		//创建TouchAction对象
		TouchAction touch1 = new TouchAction(driver);

		/**
		 * 遍历短信验证码数组charMsg，用tap去点击绝对坐标实现输入短验的操作
		 * （webview页面TouchAction对象的tap方法目前只能点击绝对死坐标，如果用元素的getLocation.getX()、getLocation.getY()方法获取的坐标，再点击的时候会有很大偏差）
		 * 1=168,1096
		 * 2=500,1096
		 * 3=835,1096
		 * 4=168,1237
		 * 5=500,1237
		 * 6=835，1237
		 * 7=168,1380
		 * 8=500,1380
		 * 9=835,1380
		 * 0=500,1522
		 */
		for (int i=0;i<charMsg.length;i++){
			if (String.valueOf(charMsg[i]).equals("0")){
				touch1.tap(PointOption.point(585,1787)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("1")){
				touch1.tap(PointOption.point(194,1287)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("2")){
				touch1.tap(PointOption.point(525,1287)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("3")){
				touch1.tap(PointOption.point(977,1287)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("4")){
				touch1.tap(PointOption.point(194,1453)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("5")){
				touch1.tap(PointOption.point(525,1453)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("6")){
				touch1.tap(PointOption.point(977,1453)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("7")){
				touch1.tap(PointOption.point(194,1618)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("8")){
				touch1.tap(PointOption.point(525,1618)).release().perform();
				Thread.sleep(2000);
				continue;
			}else if (String.valueOf(charMsg[i]).equals("9")){
				touch1.tap(PointOption.point(977,1618)).release().perform();
				Thread.sleep(2000);
				continue;
			}
		}

		Thread.sleep(3000);

		if (driver.getPageSource().contains("您的提现申请正在审核中，点击刷新可查看最新状态")){
			log.info("进件状态为：{}",getByLocation.getElementObj("jumpToWithDrawingPage",driver).getText());
		}else{
			log.error("提现申请提交失败！");
		}
		//显式等待6分钟，判断是否进入放款中页面
		WebDriverWait waitForStatusLoaning=new WebDriverWait(driver, 360);
		AndroidElement loaningStatus = (AndroidElement)waitForStatusLoaning.until(ExpectedConditions.presenceOfElementLocated(getByLocation.getLocatorApp("jumpToLoadingPage")));
		log.info("进入放款中页面:{}",getByLocation.getElementObj("loadingPageEle",driver).getText());

		//显式等待15分钟，判断是否放款成功
		WebDriverWait waitForStatusSuccessLoan = new WebDriverWait(driver,900);
		AndroidElement loanSuccess =  (AndroidElement)waitForStatusSuccessLoan.until(ExpectedConditions.presenceOfElementLocated(getByLocation.getLocatorApp("jumpLoanSucess")));
		log.info("进件状态为：{}",getByLocation.getElementObj("loanSuccessPageEle",driver).getText());
		//点击放款成功页面的返回按钮
		getByLocation.getElementObj("loanSuccessBackBtn",driver).click();
		Thread.sleep(3000);
		driver.context("NATIVE_APP");
		if (driver.getPageSource().contains("额度已用完")){
			log.info("成功返回app首页");
		}else{
			log.error("返回到首页失败！");
		}

	}

	/**
	 * 去还款的流程
	 * @throws InterruptedException
	 */
	public void goRepay() throws InterruptedException {

		GetByLocation getByLocation = new GetByLocation("goRepay.properties");
		//点击查看借款详情，进入借款列表页面
		getByLocation.getElementObj("goCrashDetail",driver).click();
		log.info("点击首页的查看借款详情按钮，进入借款列表页面");
		//点击借款列表中的待还订单
		Thread.sleep(4000);
		getByLocation.getElementObj("orderList",driver).click();
		log.info("点击借款列表中的待还订单，进入还款页面");
		Thread.sleep(4000);
		AppiumUtils.setContextToWebview(driver);
		//点击立即还款
		getByLocation.getElementObj("goRepayRightNow",driver).click();
		Thread.sleep(3000);
		//再次点击立即还款
		getByLocation.getElementObj("goRepay4Phrase",driver).click();
		Thread.sleep(3000);
		log.info("支付金额为：{}",getByLocation.getElementObj("amountShouyinTai",driver).getText());
		//去支付
		TouchAction touchRepay = new TouchAction(driver);
		touchRepay.tap(PointOption.point(634,1089)).release().perform();
		//driver.findElementByXPath("//*[@id='pay']/label").click();

		Thread.sleep(2000);
		getByLocation.getElementObj("shouyintaiMsg",driver).sendKeys("111111");
		Thread.sleep(2000);
		TouchAction firmbtn = new TouchAction(driver);
		firmbtn.tap(PointOption.point(648,1329)).release().perform();

		Thread.sleep(3000);
		if (driver.getPageSource().contains("消费成功")){
			log.info("支付成功");
		}else{
			log.error("支付有误");
		}
		//点击支付结果弹窗的确定按钮
		//498,989
		TouchAction firmByPoint = new TouchAction(driver);
		firmByPoint.tap(PointOption.point(650,1286)).release().perform();
		Thread.sleep(8000);
		driver.context("NATIVE_APP");
		if (driver.getPageSource().contains("借款列表")){
			log.info("第一期支付成功后返回到借款列表页面");
		}else{
			log.error("返回到借款列表页失败");
		}

		//点击借款列表中的待还订单
		getByLocation.getElementObj("orderList",driver).click();
		log.info("点击借款列表中的待还订单，进入还款页面");
		Thread.sleep(4000);
		AppiumUtils.setContextToWebview(driver);
		//点击提前还款
		getByLocation.getElementObj("repayAdvance",driver).click();
		Thread.sleep(2000);
		//点击弹窗同意¶
		getByLocation.getElementObj("agreeAdvancePayBtn",driver).click();
		Thread.sleep(3000);
		log.info("剩余期数提前还款金额为：{}",getByLocation.getElementObj("surplusAdvanceAmount",driver).getText());
		//再次点击提前还款
		getByLocation.getElementObj("repayAdvanceAgain",driver).click();
		Thread.sleep(3000);

		//收银台点击去支付
		TouchAction touchRepay2 = new TouchAction(driver);
		touchRepay2.tap(PointOption.point(634,1089)).release().perform();
		Thread.sleep(2000);
		getByLocation.getElementObj("shouyintaiMsg",driver).sendKeys("111111");

		Thread.sleep(2000);
		TouchAction firmbtn2 = new TouchAction(driver);
		firmbtn2.tap(PointOption.point(648,1329)).release().perform();

		Thread.sleep(3000);
		if (driver.getPageSource().contains("消费成功")){
			log.info("支付成功");
		}else{
			log.error("支付有误");
		}
		//点击支付结果弹窗的确定按钮
		TouchAction firmByPoint1 = new TouchAction(driver);
		firmByPoint1.tap(PointOption.point(650,1286)).release().perform();
		Thread.sleep(8000);

		driver.context("NATIVE_APP");
		if (driver.getPageSource().contains("借款列表")){
			log.info("剩余期数提前结清支付成功后返回到借款列表页面");
		}else{
			log.error("返回到借款列表页失败");
		}
	}

	
//	public static void main(String[] args) throws Exception {
//
//		/**
//		 * 华为荣耀：udid=VBJDU18422013362
//		 * momo模拟器：emulator-5554
//		 */
//		AndroidDriver<AndroidElement> driver = InitDriver.initDriverWithInstall("emulator-5554","com.renmai.easymoney","com.renmai.easymoney.MainActivity");
//
//		Thread.sleep(5000);
//		DemoCase demo = new DemoCase(driver);
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		demo.login();
//		demo.idoAuthAndSupplementInfo();
//		demo.withdrawApply();
//		demo.goRepay();
//
//	}


}
