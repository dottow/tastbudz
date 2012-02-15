package com.tastbudz.dao.hibernate.usertype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.CurrencyType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.tastbudz.model.Price;


public class PriceUserType implements CompositeUserType {
	private static final String CAST_EXCEPTION_TEXT = " cannot be cast to a com.tastbudz.model.Price.";

	public String[] getPropertyNames() {
		// ORDER IS IMPORTANT!  it must match the order the columns are defined in the property mapping
		return new String[] { "amount", "currency" };
	}

	public Type[] getPropertyTypes() {
		return new Type[] { BigDecimalType.INSTANCE, CurrencyType.INSTANCE };
	}

	public Class returnedClass() {
		return Price.class;
	}

	public Object getPropertyValue(Object component, int propertyIndex) {
        if ( component == null ) {
            return null;
        }

        final Price Price = (Price) component;
        switch ( propertyIndex ) {
            case 0: {
                return Price.getAmount();
            }
            case 1: {
                return Price.getCurrency();
            }
            default: {
                throw new HibernateException( "Invalid property index [" + propertyIndex + "]" );
            }
        }
    }

    public void setPropertyValue(Object component, int propertyIndex, Object value) throws HibernateException {
        if ( component == null ) {
            return;
        }

        final Price Price = (Price) component;
        switch ( propertyIndex ) {
            case 0: {
                Price.setAmount( (BigDecimal) value );
                break;
            }
            case 1: {
                Price.setCurrency( (Currency) value );
                break;
            }
            default: {
                throw new HibernateException( "Invalid property index [" + propertyIndex + "]" );
            }
        }
    }

	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
        assert names.length == 2;
        BigDecimal amount = (BigDecimal)BigDecimalType.INSTANCE.get( rs, names[0] , session); // already handles null check
        Currency currency = (Currency)CurrencyType.INSTANCE.get( rs, names[1], session ); // already handles null check
        if (amount == null || currency == null) return null;
        
        Price price = new Price();
        price.setAmount(amount);
        price.setCurrency(currency);
        return price;
    }

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
	throws HibernateException, SQLException {
        if ( value == null ) {
            BigDecimalType.INSTANCE.set( st, null, index, session );
            CurrencyType.INSTANCE.set( st, null, index+1, session );
        }
        else {
            final Price Price = (Price) value;
            BigDecimalType.INSTANCE.set( st, Price.getAmount(), index, session );
            CurrencyType.INSTANCE.set( st, Price.getCurrency(), index+1, session );
        }
    }

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;

		if (!Price.class.isAssignableFrom(x.getClass())) {
			throw new HibernateException(x.getClass().toString()
					+ CAST_EXCEPTION_TEXT);
		} else if (!Price.class.isAssignableFrom(y.getClass())) {
			throw new HibernateException(y.getClass().toString()
					+ CAST_EXCEPTION_TEXT);
		}

		Price a = (Price) x;
		Price b = (Price) y;

		return a.equals(b);
	}

	public int hashCode(Object x) throws HibernateException {
		if (!Price.class.isAssignableFrom(x.getClass()))
			throw new HibernateException(x.getClass().toString()
					+ CAST_EXCEPTION_TEXT);

		return x.hashCode();
	}

	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) return null;
		
		if (!Price.class.isAssignableFrom(value.getClass())) {
			throw new HibernateException(value.getClass().toString()
					+ CAST_EXCEPTION_TEXT);
		}
		Price price = (Price)value;
		Price copy = new Price();
		copy.setAmount(price.getAmount());
		copy.setCurrency(price.getCurrency());
		return copy;
	}

	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value, SessionImplementor session)
			throws HibernateException {
		return (Serializable)deepCopy(value);
	}

	@Override
	public Object assemble(Serializable cached, SessionImplementor session,
			Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	@Override
	public Object replace(Object original, Object target,
			SessionImplementor session, Object owner) throws HibernateException {
		return deepCopy(original);
	}

}
