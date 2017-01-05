import java.util.Date;

import VO.Member;
import VO.MerchInfo;
import VO.Provide;
import VO.Sale;
import VO.User;

import com.smss.linkDB.DAOFactory;




public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		Provide pro = new Provide("王老吉", "广州市", "18813960106");
		DAOFactory.getProvideInstance().save(pro);*/
		//System.out.println(new Date());*/
		//DAOFactory.getProvideInstance().update(pro);
		//DAOFactory.getProvideInstance().delete(pro);
/*		Dealing deal =  new Dealing("8", 12.30, new Date(), "1");
		//DAOFactory.getDealingInstance().save(deal);
		//DAOFactory.getDealingInstance().delete(deal);
		System.out.println(DAOFactory.getDealingInstance().searchByID("7").getDealingDate());
		DAOFactory.getProvideInstance().save(new Provide("加多宝","广东省","13025609673"));
		DAOFactory.getDealingInstance().save(new Dealing(24.8,new Date(),"1"));*/
		//DAOFactory.getMemberInstance().save(new Member("1213132",0,new Date()));
		//System.out.println((DAOFactory.getMemberInstance().searchByID("24873426401361927")).getTotalCost());
/*		Member  m = new Member("24873426401361927", "1234", 2.1, new Date());
		DAOFactory.getMemberInstance().update(m);*/
/*		Member m = new Member();
		m.setMemberID("24873426401361927");
		DAOFactory.getMemberInstance().delete(m);*/
		//System.out.println((DAOFactory.getMemberInstance().searchByLikeProperty("MemberID", "")).size());
		//System.out.println((DAOFactory.getMemberInstance().searchByPage(1, 2)).size());
		/*DAOFactory.getMerchInfoInstance().save(new MerchInfo("百事可乐青柠",2.5,100,"6908946287346","24873426401361934"));*/
		//System.out.println((DAOFactory.getMerchInfoInstance().searchByBarCode("211221212121")).getMerchName());
		//DAOFactory.getMerchInfoInstance().update(new MerchInfo("24873426401361928","大怡宝",4.5,100,"211221212121","24873426401361925"));
/*		System.out.println((DAOFactory.getMerchInfoInstance().searchByLikeProperty("ProvideID", "1")).size());
		System.out.println((DAOFactory.getMerchInfoInstance().searchByPage(1, 1)).size());*/
/*		MerchInfo merchInfo = new MerchInfo();
		merchInfo.setMerchID("24873426401361928");
		DAOFactory.getMerchInfoInstance().delete(merchInfo);*/
		/*DAOFactory.getSaleInstance().save(new Sale("1212", new Date(), 2, 9));*/
		//System.out.println((DAOFactory.getSaleInstance().searchByBarCode("1212")).size());
/*		Sale sale = new Sale("24873426401361930","1212",new Date(),5,12);
		DAOFactory.getSaleInstance().update(sale);*/
/*		Sale sale = new Sale();
		sale.setSaleID("24873426401361930");
		DAOFactory.getSaleInstance().delete(sale);*/
/*		User user = new User("1212", "121212");
		DAOFactory.getUserInstance().save(user);*/
		//System.out.println((DAOFactory.getUserInstance().searchByID("24873426401361932")).getUserName());
/*		User user = new User("24873426401361932","1234","121212");
		DAOFactory.getUserInstance().update(user);
		System.out.println((DAOFactory.getUserInstance().searchByPage(1, 2)).size());*/
		//System.out.println((DAOFactory.getUserInstance().searchByLikeProperty("UserID", "1")).size());
		//DAOFactory.getProvideInstance().save(new Provide("得力集团有限公司","浙江宁海得力工业园","0574-65278888"));
		//DAOFactory.getMerchInfoInstance().save(new MerchInfo("康酷晶砖杯",14,100,"6952735010141","24873426401361942"));
		/*DAOFactory.getProvideInstance().save(new Provide("三只松鼠股份有限公司","安徽省芜湖市","400-800-4900"));*/
		//DAOFactory.getMerchInfoInstance().save(new MerchInfo("软香奶萨萨",23,35,"6956511907434","24873426401361944"));
		//DAOFactory.getMerchInfoInstance().save(new MerchInfo("棒棒糖",0.5,135,"6911316570306","24873426401361944"));
		//DAOFactory.getMemberInstance().save(new Member("1", 0, new Date()));
	}

}
