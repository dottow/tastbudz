@TypeDefs(
	{
		@TypeDef(name="ID", typeClass=com.tastbudz.dao.hibernate.usertype.IDUserType.class),
		@TypeDef(name="Price", typeClass=com.tastbudz.dao.hibernate.usertype.PriceUserType.class)
	}
) package com.tastbudz.model;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;



