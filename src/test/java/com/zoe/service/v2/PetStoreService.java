package com.zoe.service.v2;

import com.zoe.dao.v2.AccountDao;
import com.zoe.dao.v2.ItemDao;
import lombok.Data;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/11 16:30
 * @since JDK 1.6
 */
@Data
public class PetStoreService {

	private AccountDao accountDao;

	private ItemDao itemDao;

	private String owner;

	private int version;


}
