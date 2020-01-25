package org.codeanywhere.database.dialect.meta;

public class H2MetaTypes extends  Oracle9MetaTypes
{
	/**
	 * @return the _DT
	 */
	public MetaType get_DT()
	{
		if (_DT == null)
		{
			_DT = super.get_DT().setColumnType("TIMESTAMP").setColumnDefineTemplate("TIMESTAMP");

		}

		return _DT;
	}
}
