package com.back.phone.service;

import com.back.phone.modelNew.CountNew;
import com.back.phone.modelNew.CountNo1New;
import com.back.phone.modelNew.CountNo2New;
import com.back.phone.modelNew.CountNo3New;

public interface CountService {
	
	CountNew selectNum(String id,String Did,String yearMonth);
	
	CountNo1New selectNumNo(String id,String Did,String yearMonth);
	
	CountNo2New selectNumNo2(String id,String Did,String yearMonth);
	
	CountNo3New selectNumNo3(String id,String Did,String yearMonth);
	
}
