package com.example.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.address.AddressDTO;
import com.example.dto.review.ReviewDTO;
import com.example.entity.Minfo;
import com.example.entity.PayResult;
import com.example.service.AddressService;
import com.example.service.MemberService;
import com.example.service.Order1Service;
import com.example.service.ProductImageService;
import com.example.service.review.ReviewImageService;
import com.example.service.review.ReviewScoreService;
import com.example.service.review.ReviewService;


@Controller
@RequestMapping(value = "/member/orderinfo")
public class MyOrderinfoController {
    
    @Autowired
    Order1Service oService;

    @Autowired
    ProductImageService iService;

    @Autowired
    MemberService memberService;

    @Autowired
    AddressService aService;

    @Autowired
    ReviewService reviewservice;

    @Autowired
    ReviewImageService reviewImageService;

    @Autowired
    ReviewScoreService reviewscoreservice;

    // http://127.0.0.1:8085/QOOT1/member/orderinfo/ing.do
    @GetMapping(value = "/ing.do")
    public String orderinfoGET(
        HttpServletRequest request,
        Model model,
        @RequestParam(name = "page" , defaultValue = "1") int page
    ){

        Map<String, Object> map = new HashMap<>();

        String userid = request.getAttribute("userid").toString();
        map.put("userid", userid);
        map.put("start", (page*5)-4);
        map.put("end", page*5);
        map.put("type", 2);
        
        int count = oService.countforcustomer(map);
        if(count != -1){
            int pages = (count-1)/5 +1;
            model.addAttribute("pages", pages);
        }
        else{
            model.addAttribute("pages", 1);
        }
        
        List<PayResult> list = oService.selectType2(map);

        String tmp = "";
        
        List<List<PayResult>> result = new ArrayList<>();

		for(PayResult obj : list){

            if(!tmp.equals(obj.getPaycode())){

                map.put("paycode", obj.getPaycode());
                List<PayResult> llist = oService.selectType2ByPaycode(map);
                for(PayResult oobj : llist){
                    Long imgno =iService.selectImageNoOne(oobj.getProductno());
                    oobj.setProductimageurl("/productimage/image?no="+imgno);
                }

                result.add(llist);

                tmp = obj.getPaycode();
                
            }
            
        } 

		model.addAttribute("list", result);
        
        return "member/order";
    }

    // ???????????? ?????? ??????
    // http://127.0.0.1:8085/QOOT1/member/orderinfo/selectone.do?productno=
    @GetMapping(value = "/selectone.do")
    public String orderselectinfoGET(
        HttpServletRequest request,
        Model model,
        @RequestParam(name = "productno") Long productno,
        @RequestParam(name = "type") Long type,
        @RequestParam(name = "paycode") String paycode,
        @RequestParam(name = "userid") String userid
    ){

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("type", type);
        map.put("productno", productno);
        map.put("paycode", paycode);

        // ?????????
        String phone = memberService.memberSelectone(userid).getPhone();
        // ?????????
        String email = memberService.memberSelectone(userid).getEmail();

        PayResult result = oService.selectoneOrder(map);
        System.out.println(result);

        String date = result.getRegdate();

        Long imgno =iService.selectImageNoOne(productno);
        result.setRegdate(date.substring(0, 10));
        result.setProductimageurl("/productimage/image?no="+imgno);
		
        // ?????? ?????? ??????
        AddressDTO addressinfo = aService.selectaddressByno(result.getAddressno());
        String address = "";
        if( addressinfo != null ) {
        	if(addressinfo.getAddress3() == null){
        		address = addressinfo.getAddress() + " " + addressinfo.getAddress2();
        	}
        	else{
        		address = addressinfo.getAddress() + " " + addressinfo.getAddress2() + " " + addressinfo.getAddress3();
        	}
        	
        }
        
		model.addAttribute("obj", result);
		model.addAttribute("phone", phone);
		model.addAttribute("email", email);
		model.addAttribute("address", address);

        return "member/orderinfo";
    }

    // ????????????(????????? ????????? ??????) ?????? ??????
    // http://127.0.0.1:8085/QOOT1/member/orderinfo/complete.do
    @GetMapping(value = "/complete.do")
    public String completeorderinfoGET(
        HttpServletRequest request,
        Model model,
        @RequestParam(name = "page" , defaultValue = "1") int page
    ){

        Map<String, Object> map = new HashMap<>();

        String userid = request.getAttribute("userid").toString();
        map.put("userid", userid);
        map.put("start", (page*5)-4);
        map.put("end", page*5);
        map.put("type", 0);
        
        int count = oService.countforcustomer(map);
        if(count != -1){
            int pages = (count-1)/5 +1;
            model.addAttribute("pages", pages);
        }
        else{
            model.addAttribute("pages", 1);
        }

        List<PayResult> list = oService.selectType0(map);
		String tmp = "";
        
        List<List<PayResult>> result = new ArrayList<>();

		for(PayResult obj : list){

            if(!tmp.equals(obj.getPaycode())){

                map.put("paycode", obj.getPaycode());
                List<PayResult> llist = oService.selectType2ByPaycode(map);
                for(PayResult oobj : llist){
                    Long imgno =iService.selectImageNoOne(oobj.getProductno());
                    oobj.setProductimageurl("/productimage/image?no="+imgno);
                }

                result.add(llist);

                tmp = obj.getPaycode();
                
            }
            
        }
		model.addAttribute("list", result);

        return "member/completeorder";
    }
    
    // ?????? ????????? ?????? ?????? ??????
    // http://127.0.0.1:8085/QOOT1/member/orderinfo/myreview.do
    @GetMapping(value = "/myreview.do")
    public String myreviewlistGET(
        @RequestParam(name="page", defaultValue = "1")int page,
        HttpServletRequest request,
        Model model
    ){

        String userid = request.getAttribute("userid").toString();

        Map<String, Object> map = new HashMap<>();
        //?????????????????? ??????=
        map.put("userid", userid);
        map.put("start",(page*5)-4);
        map.put("end",page*5);

        long total = reviewservice.myReviewCount(userid);//????????????????????? ?????? ????????????

        //?????????
        List<ReviewDTO> list = reviewservice.selectlistmyreview(map);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println(list.toString());
        //?????????(???????????????) ??? ?????? list ????????? ???????????? ??????
        for(ReviewDTO review : list){


            Minfo minfo = memberService.memberinfoselectone(review.getUserid());
            review.setProfileurl(minfo.getUrl());

            //???????????? ?????????????????? ?????????,(???????????? ??????)
            List<Long> obj =reviewImageService.selectReviewImage(review.getNo());
            //????????? ?????? ????????????(????????????)
            String[] images = new String[obj.size()]; 

            Long reviewcount = reviewscoreservice.CountReviewScore(review.getNo());//??? ????????? ??????

            review.setCount(reviewcount);

            //????????????????????? ??????
            for(int i=0;i< obj.size();i++){

                images[i] = "/review/image?no="+obj.get(i);
            }
            review.setImage(images);

        }
        System.out.println(list.toString());

        model.addAttribute("list", list);
        model.addAttribute("pages", (total-1)/5+1);
        

        return "member/myreview.html";
    }

    // ?????? ????????? ??????
    // http://127.0.0.1:8085/QOOT1/member/orderinfo/myaddress.do
    @GetMapping(value = "/myaddress.do")
    public String addresslistGET(
        HttpServletRequest request,
        Model model,
        @RequestParam(name = "page", defaultValue = "1") int page
    ){
        String userid = request.getAttribute("userid").toString();

        Map<String, Object> map = new HashMap<>();

        map.put("userid", userid);
        map.put("start",(page*5)-4);
        map.put("end",page*5);
    
        List<AddressDTO> list = aService.selectListpage(map);
    
        int total = aService.countaddresstbl(userid);

        model.addAttribute("list", list);
        model.addAttribute("pages", (total-1)/5+1);

        return "member/myaddress";
    }
}
