package org.codeanywhere.database.dialect.meta;

public class Oracle9MetaTypes extends MetaTypesBase
{

	/**
	 * @return the _BIN
	 */
	public MetaType get_BIN()
	{
		if (_BIN == null)
		{

			_BIN = super.get_BIN().setColumnType("RAW").setColumnDefineTemplate("RAW($l)");

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
			_BL = super.get_BL().setColumnType("NUMBER(1,0)").setColumnDefineTemplate("NUMBER(1,0)");

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
			_BMP = super.get_BMP().setColumnType("BLOB").setColumnDefineTemplate("BLOB");

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
			_BT = super.get_BT().setColumnType("NUMBER(3,0)").setColumnDefineTemplate("NUMBER(3,0)");

		}
		return _BT;
	}

	/**
	 * @return the _D
	 */
	public MetaType get_D()
	{
		if (_D == null)
		{
			_D = super.get_D().setColumnType("DATE").setColumnDefineTemplate("DATE");

		}

		return _D;
	}

	/**
	 * @return the _DC
	 */
	public MetaType get_DC()
	{
		if (_DC == null)
		{
			_DC = super.get_DC().setColumnType("NUMERIC(19,0)").setColumnDefineTemplate("NUMERIC($p,$s)").setDefaultPrec(19);

		}
		return _DC;
	}

	/**
	 * @return the _DT
	 */
	public MetaType get_DT()
	{
		if (_DT == null)
		{
			_DT = super.get_DT().setColumnType("DATE").setColumnDefineTemplate("DATE");

		}

		return _DT;
	}

	/**
	 * @return the _F
	 */
	public MetaType get_F()
	{
		if (_F == null)
		{
			_F = super.get_F().setColumnType("FLOAT").setColumnDefineTemplate("FLOAT");

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
			_I = super.get_I().setColumnType("NUMBER(10,0)").setColumnDefineTemplate("NUMBER(10,0)").setDefaultPrec(10);

		}
		return _I;
	}

	/**
	 * @return the _LBIN
	 */
	public MetaType get_LBIN()
	{
		if (_LBIN == null)
		{
			_LBIN = super.get_LBIN().setColumnType("LONG RAW").setColumnDefineTemplate("LONG RAW").setDefaultLength(8);

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
			_LF = super.get_LF().setColumnType("DOUBLE PRECISION").setColumnDefineTemplate("DOUBLE PRECISION");

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
			_LI = super.get_LI().setColumnType("NUMBER(20,0)").setColumnDefineTemplate("NUMBER(20,0)").setDefaultPrec(20);

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
			_LVA = super.get_LVA().setColumnType("NVARCHAR2").setColumnDefineTemplate("NVARCHAR2($l)").setDefaultLength(1024);

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
			_MBT = super.get_MBT().setColumnType("RAW").setColumnDefineTemplate("RAW($l)").setDefaultLength(1024);

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
			_MN = super.get_MN().setColumnType("NUMBER(19,4)").setColumnDefineTemplate("NUMBER(19,4)").setDefaultPrec(19);

		}

		return _MN;
	}

	/**
	 * @return the _N
	 */
	public MetaType get_N()
	{
		if (_N == null)
		{
			_N = super.get_N().setColumnType("NUMBER(19,4)").setColumnDefineTemplate("NUMERIC($p,$s)").setDefaultPrec(19);

		}

		return _N;
	}

	/**
	 * @return the _NO
	 */
	public MetaType get_NO()
	{
		if (_NO == null)
		{
			_NO = super.get_NO().setColumnType("NUMBER(19,0)").setColumnDefineTemplate("NUMBER(19,0)").setDefaultPrec(19);

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
			_OLE = super.get_OLE().setColumnType("BLOB").setColumnDefineTemplate("BLOB");

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
			_PIC = super.get_PIC().setColumnType("BLOB").setColumnDefineTemplate("BLOB");

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
			_SF = super.get_SF().setColumnType("FLOAT").setColumnDefineTemplate("FLOAT");

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
			_SI = super.get_SI().setColumnType("NUMBER(5,0)").setColumnDefineTemplate("NUMBER(5,0)");

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
			_T = super.get_T().setColumnType("DATE").setColumnDefineTemplate("DATE");

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
			_TXT = super.get_TXT().setColumnType("CLOB").setColumnDefineTemplate("CLOB");

		}

		return _TXT;
	}

	/**
	 * @return the _VA
	 */
	public MetaType get_VA()
	{
		if (_VA == null)
		{
			_VA = super.get_VA().setColumnType("VARCHAR2").setColumnDefineTemplate("VARCHAR2($l)");

		}

		return _VA;
	}

	/**
	 * @return the _VBIN
	 */
	public MetaType get_VBIN()
	{
		if (_VBIN == null)
		{
			_VBIN = super.get_VBIN().setColumnType("LONG RAW").setColumnDefineTemplate("LONG RAW").setDefaultLength(1024);

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
			_VMBT = super.get_VMBT().setColumnType("RAW").setColumnDefineTemplate("RAW($l)").setDefaultLength(256);

		}

		return _VMBT;
	}

}
