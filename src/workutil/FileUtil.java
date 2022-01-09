package workutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author wangxiaohu
 * @version Id: FileUtil.java, v0.1 2022年01月07日 14:55:29 wangxiaohu Exp $
 */
public class FileUtil {



    public static void main(String[] args) throws Exception{
        File file = new File("/Users/wangxh/Desktop/苏宁POS失败的请求");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line;
        int i=1;
        List<Order> list = new ArrayList<>();
        List<String > successOrderList =  successOrderList();
        while( (line = br.readLine())!=null ){
            try{
                if((line = line.trim()).length()>0){
                    String[] arr = line.split("&");
                    String out_order_id=arr[1];
                    String lease_code=arr[2];
                    String order_price=arr[3];
                    String pay_amount=arr[4];
                    String payment_list=arr[9]; //支付
                    String goods_list=arr[10]; //支付

                    if(lease_code.equals("lease_code=sn")){
                        out_order_id = out_order_id.split("=")[1];
                        int order_price_int = Integer.parseInt(order_price.split("=")[1]);
                        int pay_amount_int = Integer.parseInt( pay_amount.split("=")[1]);
                        payment_list = URLDecoder.decode(payment_list.split("=")[1]);
                        goods_list = URLDecoder.decode(goods_list.split("=")[1]);


                        JSONArray ja = JSONObject.parseArray(payment_list);
                        String paymentMethodName = "";
                        for(int j=0;j<ja.size();j++){
                            Map o = (Map)ja.get(j);
                            if(j==0){
                                paymentMethodName = o2String(o.get("paymentMethodName"));
                            }else{
                                paymentMethodName = paymentMethodName+"+"+ o2String(o.get("paymentMethodName"));
                            }
                        }

                        ja = JSONObject.parseArray(goods_list);
                        String goods = "";
                        for(int j=0;j<ja.size();j++){
                            Map o = (Map)ja.get(j);
                            String item = o2String(o.get("categoryName")) + "["+o2String(o.get("itemName"))+"]";
                            if(j==0){
                                goods = item;
                            }else{
                                goods = goods+"+"+item;
                            }
                        }


                        Order order = new Order(out_order_id,order_price_int,pay_amount_int,
                            successOrderList.contains(out_order_id)?"成功":"失败",
                            paymentMethodName,
                            goods
                            );
                        list.add(order);
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage() + "--" + i);
            }
            i++;

        }

        list.stream().map(x->x.outOrderId).distinct().forEach(x->{
            //System.out.println("'" + x +"',");
        });

        Map<String,String> orderidmap = new HashMap();
        list =list.stream().filter(x->{
            boolean f = !orderidmap.containsKey(x.outOrderId);
            orderidmap.put(x.outOrderId,"1");
            return f;
        }).collect(Collectors.toList());


        System.out.println("----=");

        long totalCount = list.stream().map(x->x.outOrderId).distinct().count();
        long totalOrderPrice = list.stream().mapToInt(x->x.orderPrice).sum();
        long totalPayAmount = list.stream().mapToInt(x->x.payAmount).sum();
        System.out.println("受影响的订单总数："+totalCount + "，订单金额：" + toYuan(totalOrderPrice) + "，支付金额：" + toYuan(totalPayAmount));

        long totalCountSuccess = list.stream().filter(x->x.successDesc.equals("成功")).map(x->x.outOrderId).distinct().count();
        long totalCountFailed = list.stream().filter(x->!x.successDesc.equals("成功")).map(x->x.outOrderId).distinct().count();
        long totalOrderPriceFailed = list.stream().filter(x->!x.successDesc.equals("成功")).mapToInt(x->x.orderPrice).sum();
        long totalPayAmountFailed = list.stream().filter(x->!x.successDesc.equals("成功")).mapToInt(x->x.payAmount).sum();
        System.out.println("多次提交后已成功订单总数：" + totalCountSuccess);
        System.out.println("\n综上：\n最终失败订单总数："+totalCountFailed + "，订单金额：" + toYuan(totalOrderPriceFailed )+ "，支付金额：" + toYuan(totalPayAmountFailed));
    }

    private static List<String> successOrderList() throws Exception{
        File file = new File("/Users/wangxh/Desktop/苏宁POS失败请求中成功的订单");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line;
        int i=1;
        List<String> list = new ArrayList<>();
        while( (line = br.readLine())!=null ){
            list.add(line.trim());
        }
        return list;
    }
    //分转元
    private static String toYuan(long cent) throws Exception {
        int cny = Currency.getInstance("CNY").getDefaultFractionDigits();
        return BigDecimal.valueOf(cent, cny).toString();
    }

    private static String o2String(Object o){
        if(o==null){
            return "";
        }
        return o.toString();
    }
}

class Order{
    public String outOrderId;
    public int orderPrice;
    public int payAmount;
    public String successDesc;
    public String paymentMethodName;
    public String goods;
    public Order(String outOrderId,int orderPrice,int payAmount, String successDesc,String paymentMethodName,String goods){
        this.outOrderId=outOrderId;
        this.orderPrice=orderPrice;
        this.payAmount=payAmount;
        this.successDesc=successDesc;
        this.paymentMethodName=paymentMethodName;
        this.goods=goods;
    }

    @Override
    public String toString(){
        return outOrderId+","+orderPrice+","+payAmount+","+paymentMethodName+","+goods+","+successDesc;
    }

}
