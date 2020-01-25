package org.codeanywhere.database.dialect.meta;

public class MySQLMetaTypes extends  MetaTypesBase
{
	
	/**
	 * @return the _BIN
	 */
	public MetaType get_BIN()
	{
		if (_BIN == null)
		{

			_BIN = super.get_BIN().setColumnType("TINYBLOB").setColumnDefineTemplate("RTINYBLOB");

		}
		return _BIN;
	}

	/**
	 * @return the _BL
	 */
	public MetaType get_BL()
	{
		if (_BL == null)
		{
			_BL = super.get_BL().setColumnType("TINYINT").setColumnDefineTemplate("TINYINT(1)");

		}

		return _BL;
	}

	/**
	 * @return the _BMP
	 */
	public MetaType get_BMP()
	{
		if (_BMP == null)
		{
			_BMP = super.get_BMP().setColumnType("LONGBLOB").setColumnDefineTemplate("LONGBLOB");

		}

		return _BMP;
	}

	/**
	 * @return the _BT
	 */
	public MetaType get_BT()
	{
		if (_BT == null)
		{
			_BT = super.get_BT().setColumnType("TINYINT UNSIGNED").setColumnDefineTemplate("TINYINT UNSIGNED");

		}
		return _BT;
	}


	/**
	 * @return the _DC
	 */
	public MetaType get_DC()
	{
		if (_DC == null)
		{
			_DC = super.get_DC().setColumnType("NUMERIC").setColumnDefineTemplate("NUMERIC($p,$s)");

		}
		return _DC;
	}

	

	/**
	 * @return the _F
	 */
	public MetaType get_F()
	{
		if (_F == null)
		{
			_F = super.get_F().setColumnType("FLOAT($p,$s)").setColumnDefineTemplate("FLOAT($p,$s)")					
	                 .setDefaultPrec(18)
	                 .setDefaultScale( 4)
	                ;

		}

		return _F;
	}

	/**
	 * @return the _I
	 */
	public MetaType get_I()
	{
		if (_I == null)
		{
			_I = super.get_I().setColumnType("INTEGER").setColumnDefineTemplate("INTEGER").setDefaultPrec(10);

		}
		return _I;
	}
	/**
	 * @return the _LA
	 */
	public MetaType get_LA()
	{
		if(_LA==null)
		{
			 _LA = super.get_LA().setColumnType("VARCHAR").setColumnDefineTemplate("VARCHAR($l)");
            
		}
		return _LA;
	}

	/**
	 * @return the _LBIN
	 */
	public MetaType get_LBIN()
	{
		if (_LBIN == null)
		{
			_LBIN = super.get_LBIN().setColumnType("BLOB").setColumnDefineTemplate("BLOB") .setDefaultLength (0);

		}
		return _LBIN;
	}

	/**
	 * @return the _LF
	 */
	public MetaType get_LF()
	{
		if (_LF == null)
		{
			_LF = super.get_LF().setColumnType("DOUBLE($p,$s)").setColumnDefineTemplate("DOUBLE($p,$s)")
					.setDefaultLength(0)
                    .setDefaultPrec( 20)
                    .setDefaultScale( 4);

		}
		return _LF;
	}

	/**
	 * @return the _LI
	 */
	public MetaType get_LI()
	{
		if (_LI == null)
		{
			_LI = super.get_LI().setColumnType("BIGINT").setColumnDefineTemplate("BIGINT").setDefaultPrec(20);

		}
		return _LI;
	}

	/**
	 * @return the _LVA
	 */
	public MetaType get_LVA()
	{
		if (_LVA == null)
		{
			_LVA = super.get_LVA().setColumnType("MEDIUMTEXT").setColumnDefineTemplate("MEDIUMTEXT") .setDefaultLength (0);

		}
		return _LVA;
	}

	/**
	 * @return the _MBT
	 */
	public MetaType get_MBT()
	{
		if (_MBT == null)
		{
			_MBT = super.get_MBT().setColumnType("BLOB").setColumnDefineTemplate("BLOB").setDefaultLength (0)
	                ;

		}

		return _MBT;
	}

	/**
	 * @return the _MN
	 */
	public MetaType get_MN()
	{
		if (_MN == null)
		{
			_MN = super.get_MN().setColumnType("NUMERIC(19,4)").setColumnDefineTemplate("NUMERIC(19,4)");

		}

		return _MN;
	}

	
	/**
	 * @return the _NO
	 */
	public MetaType get_NO()
	{
		if (_NO == null)
		{
			_NO = super.get_NO().setColumnType("INTEGER").setColumnDefineTemplate("INTEGER");

		}

		return _NO;
	}

	/**
	 * @return the _OLE
	 */
	public MetaType get_OLE()
	{
		if (_OLE == null)
		{
			_OLE = super.get_OLE().setColumnType("LONGBLOB").setColumnDefineTemplate("LONGBLOB");

		}

		return _OLE;
	}

	/**
	 * @return the _PIC
	 */
	public MetaType get_PIC()
	{
		if (_PIC == null)
		{
			_PIC = super.get_PIC().setColumnType("LONGBLOB").setColumnDefineTemplate("LONGBLOB");

		}
		return _PIC;
	}

	/**
	 * @return the _SF
	 */
	public MetaType get_SF()
	{
		if (_SF == null)
		{
			_SF = super.get_SF().setColumnType("FLOAT($p,$s)").setColumnDefineTemplate("FLOAT($p,$s)")  .setDefaultPrec(18)
	                 .setDefaultScale( 4)
	                ;

		}

		return _SF;
	}

	/**
	 * @return the _SI
	 */
	public MetaType get_SI()
	{
		if (_SI == null)
		{
			_SI = super.get_SI().setColumnType("SMALLINT").setColumnDefineTemplate("SMALLINT");

		}

		return _SI;
	}

	/**
	 * @return the _T
	 */
	public MetaType get_T()
	{
		if (_T == null)
		{
			_T = super.get_T().setColumnType("TIME").setColumnDefineTemplate("TIME");

		}

		return _T;
	}

	/**
	 * @return the _TS
	 */
	public MetaType get_TS()
	{
		if (_TS == null)
		{
			_TS = super.get_TS().setColumnType("TIMESTAMP").setColumnDefineTemplate("TIMESTAMP");

		}

		return _TS;
	}

	/**
	 * @return the _TXT
	 */
	public MetaType get_TXT()
	{
		if (_TXT == null)
		{
			_TXT = super.get_TXT().setColumnType("TEXT").setColumnDefineTemplate("TEXT");

		}

		return _TXT;
	}

	

	/**
	 * @return the _VBIN
	 */
	public MetaType get_VBIN()
	{
		if (_VBIN == null)
		{
			_VBIN = super.get_VBIN().setColumnType("BLOB").setColumnDefineTemplate("LONG RAWBLOB");

		}

		return _VBIN;
	}

	/**
	 * @return the _VMBT
	 */
	public MetaType get_VMBT()
	{
		if (_VMBT == null)
		{
			_VMBT = super.get_VMBT().setColumnType("MEDIUMBLOB").setColumnDefineTemplate("MEDIUMBLOB");

		}

		return _VMBT;
	}
}
