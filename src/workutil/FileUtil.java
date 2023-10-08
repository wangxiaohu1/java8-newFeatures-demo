//package workutil;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.math.BigDecimal;
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.Currency;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @author wangxiaohu
// * @version Id: FileUtil.java, v0.1 2022年01月07日 14:55:29 wangxiaohu Exp $
// */
//public class FileUtil {
//
//    private static String lease = "lease_code=gmc";
//    private static String root_dir = "/Users/wangxh/Desktop/光美POS卖品下单失败/";
//    private static File file_cinema_name = new File(root_dir+"租户影城");
//    private static File file = new File(root_dir+"POS失败的请求");
//    private static File file_success = new File(root_dir+"POS失败请求中成功的订单");
//    private static File file_final = new File(root_dir+"POS下单最终数据");
//    // iconv -f UTF8 -t GB18030 POS下单最终数据>POS下单最终数据1
//
//    public static void main(String[] args) throws Exception{
//        FileReader reader = new FileReader(file);
//        BufferedReader br = new BufferedReader(reader);
//        String line;
//        int i=1;
//        List<Order> list = new ArrayList<>();
//        List<String > successOrderList =  successOrderList();
//        Map<String,String> cinemamap = readCinema();
//        while( (line = br.readLine())!=null ){
//            try{
//                if((line = line.trim()).length()>0){
//                    String[] arr = line.split("&");
//                    String cinema_link_id=arr[0];
//                    String out_order_id=arr[1];
//                    String lease_code=arr[2];
//                    String order_price=arr[3];
//                    String pay_amount=arr[4];
//                    //支付
//                    String payment_list=arr[9];
//                    //支付
//                    String goods_list=arr[10];
//
//                    if(lease_code.equals(lease)){
//                        cinema_link_id = cinema_link_id.split("=")[1];
//                        out_order_id = out_order_id.split("=")[1];
//                        int order_price_int = Integer.parseInt(order_price.split("=")[1]);
//                        int pay_amount_int = Integer.parseInt( pay_amount.split("=")[1]);
//                        payment_list = URLDecoder.decode(payment_list.split("=")[1]);
//                        goods_list = URLDecoder.decode(goods_list.split("=")[1]);
//
//
//                        JSONArray ja = JSONObject.parseArray(payment_list);
//                        String paymentMethodName = "";
//                        for(int j=0;j<ja.size();j++){
//                            Map o = (Map)ja.get(j);
//                            if(j==0){
//                                paymentMethodName = o2String(o.get("paymentMethodName"));
//                            }else{
//                                paymentMethodName = paymentMethodName+"+"+ o2String(o.get("paymentMethodName"));
//                            }
//                        }
//
//                        ja = JSONObject.parseArray(goods_list);
//                        String goods = "";
//                        for(int j=0;j<ja.size();j++){
//                            Map o = (Map)ja.get(j);
//                            String item = o2String(o.get("categoryName")) + "["+o2String(o.get("itemName"))+"]";
//                            if(j==0){
//                                goods = item;
//                            }else{
//                                goods = goods+"+"+item;
//                            }
//                        }
//
//
//                        Order order = new Order(cinema_link_id,cinemamap.get(cinema_link_id), out_order_id,order_price_int,pay_amount_int,
//                            successOrderList.contains(out_order_id)?"成功":"失败",
//                            paymentMethodName,
//                            goods
//                            );
//                        list.add(order);
//                    }
//                }
//            }catch (Exception e){
//                System.out.println(e.getMessage() + "--" + i);
//            }
//            i++;
//
//        }
//        StringBuffer sb = new StringBuffer();
//        list.stream().map(x->x.outOrderId).distinct().forEach(x->{
//            //System.out.println("'" + x +"',");
//            sb.append("'" + x +"',\n");
//
//        });
//        //拿着这个数据去lark_data_adb查询
//        String sql= "SELECT * FROM `trade_order_group`"+
//        "where"+
//            "`out_id` in (" + sb.delete(sb.length()-2,sb.length()).toString()+")"+
//        "and `order_status` ='2'"+
//        " and `pay_status` ='3'";
//        System.out.println("//拿着这个数据去lark_data_adb查询，过滤出出成功的订单out_id,放到[POS失败请求中成功的订单]文件中");
//        System.out.println(sql);
//
//        Map<String,String> orderidmap = new HashMap();
//        list =list.stream().filter(x->{
//            boolean f = !orderidmap.containsKey(x.outOrderId);
//            orderidmap.put(x.outOrderId,"1");
//            return f;
//        }).collect(Collectors.toList());
//
//        System.out.println("----=");
//        //输出表格
//        writeFinal(list);
//
//
//        System.out.println("----=");
//
//
//        long totalCount = list.stream().map(x->x.outOrderId).distinct().count();
//        long totalOrderPrice = list.stream().mapToInt(x->x.orderPrice).sum();
//        long totalPayAmount = list.stream().mapToInt(x->x.payAmount).sum();
//        System.out.println("受影响的订单总数："+totalCount + "，订单金额：" + toYuan(totalOrderPrice) + "，支付金额：" + toYuan(totalPayAmount));
//
//        long totalCountSuccess = list.stream().filter(x->x.successDesc.equals("成功")).map(x->x.outOrderId).distinct().count();
//        long totalCountFailed = list.stream().filter(x->!x.successDesc.equals("成功")).map(x->x.outOrderId).distinct().count();
//        long totalOrderPriceFailed = list.stream().filter(x->!x.successDesc.equals("成功")).mapToInt(x->x.orderPrice).sum();
//        long totalPayAmountFailed = list.stream().filter(x->!x.successDesc.equals("成功")).mapToInt(x->x.payAmount).sum();
//        System.out.println("多次提交后已成功订单总数：" + totalCountSuccess);
//        System.out.println("\n综上：\n最终失败订单总数："+totalCountFailed + "，订单金额：" + toYuan(totalOrderPriceFailed )+ "，支付金额：" + toYuan(totalPayAmountFailed));
//    }
//
//    private static List<String> successOrderList() throws Exception{
//        FileReader reader = new FileReader(file_success);
//        BufferedReader br = new BufferedReader(reader);
//        String line;
//        List<String> list = new ArrayList<>();
//        while( (line = br.readLine())!=null ){
//            list.add(line.trim());
//        }
//        return list;
//    }
//    private static Map<String,String> readCinema()throws Exception{
//        FileReader reader = new FileReader(file_cinema_name);
//        BufferedReader br = new BufferedReader(reader);
//        String line;
//        Map<String,String> map = new HashMap<>();
//        while( (line = br.readLine())!=null ){
//            String[] ar = line.split("\t");
//            map.put(ar[0],ar[1]);
//        }
//        return map;
//    }
//    //写最终数据cvs
//    private static void writeFinal(List<Order> list) throws Exception{
//        FileWriter fw = new FileWriter(file_final);
//        BufferedWriter bw = new BufferedWriter(fw);
//        fw.write("影院编码,影院名称,订单号,订单金额（分）,支付金额（分）,支付方式,卖品,是否成功\n");
//        for(Order o:list){
//            bw.write(o.toString() +"\n");
//            //fw.write(o.toString()+"\n");
//        }
//        bw.flush();
//    }
//
//    //分转元
//    private static String toYuan(long cent) throws Exception {
//        int cny = Currency.getInstance("CNY").getDefaultFractionDigits();
//        return BigDecimal.valueOf(cent, cny).toString();
//    }
//
//    private static String o2String(Object o){
//        if(o==null){
//            return "";
//        }
//        return o.toString();
//    }
//}
//
//class Order{
//    public String cinemaLinkId;
//    public String cineamName;
//    public String outOrderId;
//    public int orderPrice;
//    public int payAmount;
//    public String successDesc;
//    public String paymentMethodName;
//    public String goods;
//    public Order(String cinemaLinkId,String cineamName,String outOrderId,int orderPrice,int payAmount, String successDesc,String paymentMethodName,String goods){
//        this.cineamName=cineamName;
//        this.cinemaLinkId=cinemaLinkId;
//        this.outOrderId=outOrderId;
//        this.orderPrice=orderPrice;
//        this.payAmount=payAmount;
//        this.successDesc=successDesc;
//        this.paymentMethodName=paymentMethodName;
//        this.goods=goods;
//    }
//
//    @Override
//    public String toString(){
//        return cinemaLinkId+","+cineamName+","+outOrderId+","+orderPrice+","+payAmount+","+paymentMethodName+","+goods+","+successDesc;
//    }
//
//
//
//}
