package org.codeanywhere.database.dialect.meta;

public class SqliteMetaTypes extends  MetaTypesBase
{

	/**
	 * @return the _BIN
	 */
	public MetaType get_BIN()
	{
		if (_BIN == null)
		{

			_BIN = super.get_BIN().setColumnType("binary").setColumnDefineTemplate("binary($l)");

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
			_BL = super.get_BL().setColumnType("bit").setColumnDefineTemplate("bit");

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
			_BMP = super.get_BMP().setColumnType("blob").setColumnDefineTemplate("blob");

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
			_BT = super.get_BT().setColumnType("tinyint").setColumnDefineTemplate("tinyint");

		}
		return _BT;
	}

	/**
	 * @return the _D
	 */
	public MetaType get_D()
	{
		if(_D==null)
		{
			_D =super.get_D().setColumnType("datetime").setColumnDefineTemplate("datetime");
            
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
			_DC = super.get_DC().setColumnType("decimal").setColumnDefineTemplate("decimal($p,$s)");

		}
		return _DC;
	}
	/**
	 * @return the _DT
	 */
	public MetaType get_DT()
	{
		if(_DT==null)
		{
			_DT=super.get_DT().setColumnType("TIMESTAMP").setColumnDefineTemplate("TIMESTAMP default (datetime('now', 'localtime')) ");
            
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
			_F = super.get_F().setColumnType("float").setColumnDefineTemplate("float")	
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
			_I = super.get_I().setColumnType("int").setColumnDefineTemplate("int");

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
			 _LA = super.get_LA().setColumnType("nchar").setColumnDefineTemplate("nchar($l)");
            
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
			_LBIN = super.get_LBIN().setColumnType("binary").setColumnDefineTemplate("binary($l)") .setDefaultLength (0);

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
			_LF = super.get_LF().setColumnType("float").setColumnDefineTemplate("float");

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
			_LI = super.get_LI().setColumnType("integer").setColumnDefineTemplate("integer").setDefaultPrec(20);

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
			_LVA = super.get_LVA().setColumnType("nvarchar").setColumnDefineTemplate("nvarchar($l)") .setDefaultLength (50);

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
			_MBT = super.get_MBT().setColumnType("binary").setColumnDefineTemplate("binary($l)").setDefaultLength (64)
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
			_MN = super.get_MN().setColumnType("money").setColumnDefineTemplate("money");

		}

		return _MN;
	}
	/**
	 * @return the _N
	 */
	public MetaType get_N()
	{
		if(_N==null)
		{
			_N = super.get_N().setColumnType("numeric").setColumnDefineTemplate("numeric($p,$s)");
			
            
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
			_NO = super.get_NO().setColumnType("numeric(18,0)").setColumnDefineTemplate("numeric(18,0)");

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
			_OLE = super.get_OLE().setColumnType("blob").setColumnDefineTemplate("blob");

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
			_PIC = super.get_PIC().setColumnType("blob").setColumnDefineTemplate("blob");

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
			_SF = super.get_SF().setColumnType("real").setColumnDefineTemplate("real")              ;

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
			_SI = super.get_SI().setColumnType("smallint").setColumnDefineTemplate("smallint");

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
			_T = super.get_T().setColumnType("datetime").setColumnDefineTemplate("datetime");

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
			_TS = super.get_TS().setColumnType("timestamp").setColumnDefineTemplate("timestamp");

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
			_TXT = super.get_TXT().setColumnType("text").setColumnDefineTemplate("text");

		}

		return _TXT;
	}

	/**
	 * @return the _VA
	 */
	public MetaType get_VA()
	{
		if(_VA==null)
		{
			_VA= super.get_VA().setColumnType("varchar").setColumnDefineTemplate("varchar($l)");
            
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
			_VBIN = super.get_VBIN().setColumnType("binary").setColumnDefineTemplate("binary($l)");

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
			_VMBT = super.get_VMBT().setColumnType("binary").setColumnDefineTemplate("binary($l)");

		}

		return _VMBT;
	}
}