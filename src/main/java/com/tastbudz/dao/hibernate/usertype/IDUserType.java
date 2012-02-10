package com.tastbudz.dao.hibernate.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import com.tastbudz.model.id.ID;


public class IDUserType implements UserType {

	private static final String CAST_EXCEPTION_TEXT = " cannot be cast to a java.util.ID.";

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		if (!byte[].class.isAssignableFrom(cached.getClass())) {
			return null;
		}
		return ID.fromBytes((byte[]) cached);
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return ((ID) value).getBytes();
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;

		if (!ID.class.isAssignableFrom(x.getClass())) {
			throw new HibernateException(x.getClass().toString()
					+ CAST_EXCEPTION_TEXT);
		} else if (!ID.class.isAssignableFrom(y.getClass())) {
			throw new HibernateException(y.getClass().toString()
					+ CAST_EXCEPTION_TEXT);
		}

		ID a = (ID) x;
		ID b = (ID) y;

		return a.equals(b);
	}

	public int hashCode(Object x) throws HibernateException {
		if (!ID.class.isAssignableFrom(x.getClass()))
			throw new HibernateException(x.getClass().toString()
					+ CAST_EXCEPTION_TEXT);

		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		byte[] value = rs.getBytes(names[0]);
		return (value == null) ? null : ID.fromBytes(value);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
			return;
		}

		if (!ID.class.isAssignableFrom(value.getClass()))
			throw new HibernateException(value.getClass().toString()
					+ CAST_EXCEPTION_TEXT);

		st.setBytes(index, (((ID) value).getBytes()));
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	@SuppressWarnings("unchecked")
	public Class returnedClass() {
		return ID.class;
	}

	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}
}
