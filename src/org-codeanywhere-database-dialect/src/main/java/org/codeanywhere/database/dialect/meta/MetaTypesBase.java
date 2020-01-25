package org.codeanywhere.database.dialect.meta;

import org.codeanywhere.framework.orm.annotation.FieldMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaTypesBase {
    protected static Logger log = LoggerFactory.getLogger(MetaTypesBase.class);
    protected MetaType _A;

    protected MetaType _BIN;

    protected MetaType _BL;

    protected MetaType _BMP;

    protected MetaType _BT;

    protected MetaType _D;

    protected MetaType _DC;

    protected MetaType _DT;

    protected MetaType _F;

    protected MetaType _I;

    protected MetaType _LA;

    protected MetaType _LBIN;

    protected MetaType _LF;

    protected MetaType _LI;

    protected MetaType _LVA;

    protected MetaType _MBT;

    protected MetaType _MN;

    protected MetaType _N;

    protected MetaType _NO;

    protected MetaType _OLE;

    protected MetaType _PIC;

    protected MetaType _SF;

    protected MetaType _SI;

    protected MetaType _T;

    protected MetaType _TS;

    protected MetaType _TXT;

    protected MetaType _VA;

    protected MetaType _VBIN;

    protected MetaType _VMBT;


    /**
     * @return the _A
     */
    public MetaType get_A() {
        if (_A == null) {
            _A = new MetaType()
                    .setAbstractTypeID("A")
                    .setColumnType("CHAR")
                    .setColumnDefineTemplate("CHAR($l)")
                    .setDefaultLength(50)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _A;
    }


    /**
     * @return the _BIN
     */
    public MetaType get_BIN() {
        if (_BIN == null) {
            _BIN = new MetaType()
                    .setAbstractTypeID("BIN")
                    .setColumnType("VARBINARY")
                    .setColumnDefineTemplate("VARBINARY($l)")
                    .setDefaultLength(128)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _BIN;
    }


    /**
     * @return the _BL
     */
    public MetaType get_BL() {
        if (_BL == null) {
            _BL = new MetaType()
                    .setAbstractTypeID("BL")
                    .setColumnType("BIT")
                    .setColumnDefineTemplate("BIT")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(1)
                    .setIsfixed(true);

        }

        return _BL;
    }


    /**
     * @return the _BMP
     */
    public MetaType get_BMP() {
        if (_BMP == null) {
            _BMP = new MetaType()
                    .setAbstractTypeID("BMP")
                    .setColumnType("VARBINARY(MAX)")
                    .setColumnDefineTemplate("VARBINARY(MAX)")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _BMP;
    }


    /**
     * @return the _BT
     */
    public MetaType get_BT() {
        if (_BT == null) {
            _BT = new MetaType()
                    .setAbstractTypeID("BT")
                    .setColumnType("TINYINT")
                    .setColumnDefineTemplate("TINYINT")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(1)
                    .setIsfixed(true);

        }
        return _BT;
    }


    /**
     * @return the _D
     */
    public MetaType get_D() {
        if (_D == null) {
            _D = new MetaType()
                    .setAbstractTypeID("D")
                    .setColumnType("DATETIME")
                    .setColumnDefineTemplate("DATETIME")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(3)
                    .setIsfixed(true);

        }

        return _D;
    }


    /**
     * @return the _DC
     */
    public MetaType get_DC() {
        if (_DC == null) {
            _DC = new MetaType()
                    .setAbstractTypeID("DC")
                    .setColumnType("DECIMAL")
                    .setColumnDefineTemplate("DECIMAL($p,$s)")
                    .setDefaultLength(0)
                    .setDefaultPrec(18)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _DC;
    }


    /**
     * @return the _DT
     */
    public MetaType get_DT() {
        if (_DT == null) {
            _DT = new MetaType()
                    .setAbstractTypeID("DT")
                    .setColumnType("DATETIME")
                    .setColumnDefineTemplate("DATETIME")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(8)
                    .setIsfixed(true);

        }

        return _DT;
    }


    /**
     * @return the _F
     */
    public MetaType get_F() {
        if (_F == null) {
            _F = new MetaType()
                    .setAbstractTypeID("F")
                    .setColumnType("REAL")
                    .setColumnDefineTemplate("REAL")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(8)
                    .setIsfixed(true);

        }

        return _F;
    }


    /**
     * @return the _I
     */
    public MetaType get_I() {
        if (_I == null) {
            _I = new MetaType()
                    .setAbstractTypeID("I")
                    .setColumnType("INT")
                    .setColumnDefineTemplate("INT")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(4)
                    .setIsfixed(true);

        }
        return _I;
    }


    /**
     * @return the _LA
     */
    public MetaType get_LA() {
        if (_LA == null) {
            _LA = new MetaType()
                    .setAbstractTypeID("LA")
                    .setColumnType("CHAR")
                    .setColumnDefineTemplate("CHAR($l)")
                    .setDefaultLength(50)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _LA;
    }


    /**
     * @return the _LBIN
     */
    public MetaType get_LBIN() {
        if (_LBIN == null) {
            _LBIN = new MetaType()
                    .setAbstractTypeID("LA")
                    .setColumnType("VARBINARY")
                    .setColumnDefineTemplate("VARBINARY($l)")
                    .setDefaultLength(256)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _LBIN;
    }


    /**
     * @return the _LF
     */
    public MetaType get_LF() {
        if (_LF == null) {
            _LF = new MetaType()
                    .setAbstractTypeID("LF")
                    .setColumnType("FLOAT(53)")
                    .setColumnDefineTemplate("FLOAT(53)")
                    .setDefaultLength(0)
                    .setDefaultPrec(53)
                    .setDefaultScale(0)
                    .setFixedLength(8)
                    .setIsfixed(true);

        }
        return _LF;
    }


    /**
     * @return the _LI
     */
    public MetaType get_LI() {
        if (_LI == null) {
            _LI = new MetaType()
                    .setAbstractTypeID("LI")
                    .setColumnType("BIGINT")
                    .setColumnDefineTemplate("BIGINT")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(8)
                    .setIsfixed(true);

        }
        return _LI;
    }


    /**
     * @return the _LVA
     */
    public MetaType get_LVA() {
        if (_LVA == null) {
            _LVA = new MetaType()
                    .setAbstractTypeID("LVA")
                    .setColumnType("NVARCHAR")
                    .setColumnDefineTemplate("NVARCHAR")
                    .setDefaultLength(50)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _LVA;
    }


    /**
     * @return the _MBT
     */
    public MetaType get_MBT() {
        if (_MBT == null) {
            _MBT = new MetaType()
                    .setAbstractTypeID("MBT")
                    .setColumnType("VARBINARY")
                    .setColumnDefineTemplate("VARBINARY($l)")
                    .setDefaultLength(512)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _MBT;
    }


    /**
     * @return the _MN
     */
    public MetaType get_MN() {
        if (_MN == null) {
            _MN = new MetaType()
                    .setAbstractTypeID("MN")
                    .setColumnType("MONEY")
                    .setColumnDefineTemplate("MONEY")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(8)
                    .setIsfixed(true);

        }


        return _MN;
    }


    /**
     * @return the _N
     */
    public MetaType get_N() {
        if (_N == null) {
            _N = new MetaType()
                    .setAbstractTypeID("N")
                    .setColumnType("NUMERIC")
                    .setColumnDefineTemplate("NUMERIC($p,$s)")
                    .setDefaultLength(0)
                    .setDefaultPrec(18)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }


        return _N;
    }


    /**
     * @return the _NO
     */
    public MetaType get_NO() {
        if (_NO == null) {
            _NO = new MetaType()
                    .setAbstractTypeID("NO")
                    .setColumnType("NUMERIC(18,0)")
                    .setColumnDefineTemplate("NUMERIC(18,0)")
                    .setDefaultLength(0)
                    .setDefaultPrec(18)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }


        return _NO;
    }


    /**
     * @return the _OLE
     */
    public MetaType get_OLE() {
        if (_OLE == null) {
            _OLE = new MetaType()
                    .setAbstractTypeID("OLE")
                    .setColumnType("VARBINARY(MAX)")
                    .setColumnDefineTemplate("VARBINARY(MAX)")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }


        return _OLE;
    }


    /**
     * @return the _PIC
     */
    public MetaType get_PIC() {
        if (_PIC == null) {
            _PIC = new MetaType()
                    .setAbstractTypeID("PIC")
                    .setColumnType("VARBINARY(MAX)")
                    .setColumnDefineTemplate("VARBINARY(MAX)")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }
        return _PIC;
    }


    /**
     * @return the _SF
     */
    public MetaType get_SF() {
        if (_SF == null) {
            _SF = new MetaType()
                    .setAbstractTypeID("SF")
                    .setColumnType("REAL")
                    .setColumnDefineTemplate("REAL")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(8)
                    .setIsfixed(true);

        }

        return _SF;
    }


    /**
     * @return the _SI
     */
    public MetaType get_SI() {
        if (_SI == null) {
            _SI = new MetaType()
                    .setAbstractTypeID("SI")
                    .setColumnType("SMALLINT")
                    .setColumnDefineTemplate("SMALLINT")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(2)
                    .setIsfixed(true);

        }

        return _SI;
    }


    /**
     * @return the _T
     */
    public MetaType get_T() {
        if (_T == null) {
            _T = new MetaType()
                    .setAbstractTypeID("T")
                    .setColumnType("DATETIME")
                    .setColumnDefineTemplate("DATETIME")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _T;
    }


    /**
     * @return the _TS
     */
    public MetaType get_TS() {
        if (_TS == null) {
            _TS = new MetaType()
                    .setAbstractTypeID("TS")
                    .setColumnType("TIMESTAMP")
                    .setColumnDefineTemplate("TIMESTAMP")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _TS;
    }


    /**
     * @return the _TXT
     */
    public MetaType get_TXT() {
        if (_TXT == null) {
            _TXT = new MetaType()
                    .setAbstractTypeID("TXT")
                    .setColumnType("VARCHAR(MAX)")
                    .setColumnDefineTemplate("VARCHAR(MAX)")
                    .setDefaultLength(0)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _TXT;
    }


    /**
     * @return the _VA
     */
    public MetaType get_VA() {
        if (_VA == null) {
            _VA = new MetaType()
                    .setAbstractTypeID("VA")
                    .setColumnType("VARCHAR")
                    .setColumnDefineTemplate("VARCHAR($l)")
                    .setDefaultLength(50)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _VA;
    }


    /**
     * @return the _VBIN
     */
    public MetaType get_VBIN() {
        if (_VBIN == null) {
            _VBIN = new MetaType()
                    .setAbstractTypeID("VA")
                    .setColumnType("VARCHAR")
                    .setColumnDefineTemplate("VARCHAR($l)")
                    .setDefaultLength(50)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _VBIN;
    }


    /**
     * @return the _VMBT
     */
    public MetaType get_VMBT() {
        if (_VMBT == null) {
            _VMBT = new MetaType()
                    .setAbstractTypeID("VMBT")
                    .setColumnType("VARBINARY")
                    .setColumnDefineTemplate("VARBINARY($l)")
                    .setDefaultLength(256)
                    .setDefaultPrec(0)
                    .setDefaultScale(0)
                    .setFixedLength(-1)
                    .setIsfixed(false);

        }

        return _VMBT;
    }


    /**
     * @param _A the _A to set
     */
    public void set_A(MetaType _A) {
        this._A = _A;
    }


    /**
     * @param _BIN the _BIN to set
     */
    public void set_BIN(MetaType _BIN) {
        this._BIN = _BIN;
    }


    /**
     * @param _BL the _BL to set
     */
    public void set_BL(MetaType _BL) {
        this._BL = _BL;
    }


    /**
     * @param _BMP the _BMP to set
     */
    public void set_BMP(MetaType _BMP) {
        this._BMP = _BMP;
    }


    /**
     * @param _BT the _BT to set
     */
    public void set_BT(MetaType _BT) {
        this._BT = _BT;
    }


    /**
     * @param _D the _D to set
     */
    public void set_D(MetaType _D) {
        this._D = _D;
    }


    /**
     * @param _DC the _DC to set
     */
    public void set_DC(MetaType _DC) {
        this._DC = _DC;
    }


    /**
     * @param _DT the _DT to set
     */
    public void set_DT(MetaType _DT) {
        this._DT = _DT;
    }


    /**
     * @param _F the _F to set
     */
    public void set_F(MetaType _F) {
        this._F = _F;
    }


    /**
     * @param _I the _I to set
     */
    public void set_I(MetaType _I) {
        this._I = _I;
    }


    /**
     * @param _LA the _LA to set
     */
    public void set_LA(MetaType _LA) {
        this._LA = _LA;
    }


    /**
     * @param _LBIN the _LBIN to set
     */
    public void set_LBIN(MetaType _LBIN) {
        this._LBIN = _LBIN;
    }


    /**
     * @param _LF the _LF to set
     */
    public void set_LF(MetaType _LF) {
        this._LF = _LF;
    }


    /**
     * @param _LI the _LI to set
     */
    public void set_LI(MetaType _LI) {
        this._LI = _LI;
    }


    /**
     * @param _LVA the _LVA to set
     */
    public void set_LVA(MetaType _LVA) {
        this._LVA = _LVA;
    }


    /**
     * @param _MBT the _MBT to set
     */
    public void set_MBT(MetaType _MBT) {
        this._MBT = _MBT;
    }


    /**
     * @param _MN the _MN to set
     */
    public void set_MN(MetaType _MN) {
        this._MN = _MN;
    }


    /**
     * @param _N the _N to set
     */
    public void set_N(MetaType _N) {
        this._N = _N;
    }


    /**
     * @param _NO the _NO to set
     */
    public void set_NO(MetaType _NO) {
        this._NO = _NO;
    }


    /**
     * @param _OLE the _OLE to set
     */
    public void set_OLE(MetaType _OLE) {
        this._OLE = _OLE;
    }


    /**
     * @param _PIC the _PIC to set
     */
    public void set_PIC(MetaType _PIC) {
        this._PIC = _PIC;
    }


    /**
     * @param _SF the _SF to set
     */
    public void set_SF(MetaType _SF) {
        this._SF = _SF;
    }


    /**
     * @param _SI the _SI to set
     */
    public void set_SI(MetaType _SI) {
        this._SI = _SI;
    }


    /**
     * @param _T the _T to set
     */
    public void set_T(MetaType _T) {
        this._T = _T;
    }


    /**
     * @param _TS the _TS to set
     */
    public void set_TS(MetaType _TS) {
        this._TS = _TS;
    }


    /**
     * @param _TXT the _TXT to set
     */
    public void set_TXT(MetaType _TXT) {
        this._TXT = _TXT;
    }


    /**
     * @param _VA the _VA to set
     */
    public void set_VA(MetaType _VA) {
        this._VA = _VA;
    }


    /**
     * @param _VBIN the _VBIN to set
     */
    public void set_VBIN(MetaType _VBIN) {
        this._VBIN = _VBIN;
    }


    /**
     * @param _VMBT the _VMBT to set
     */
    public void set_VMBT(MetaType _VMBT) {
        this._VMBT = _VMBT;
    }


    /**
     * @param metaType 抽象类型
     * @return
     */
    public MetaType GetMetaType(String metaType) {
        switch (metaType.toUpperCase()) {
            case "BL": //	Boolean	DbType.Boolean
                return get_BL();

            case "BT": //	Byte	DbType.Byte
                return get_BT();
            case "MBT": //	Byte[]	DbType.Binary
                return get_MBT();
            case "VMBT": //	Byte[]	DbType.Binary
                return get_VMBT();
            case "BIN": //	Byte[]	DbType.Binary
                return get_BIN();
            case "LBIN": //	Byte[]	DbType.Binary

                return get_LBIN();
            case "BMP": //	Byte[]	DbType.Binary
                return get_BMP();
            case "PIC": //	Byte[]	DbType.Binary
                return get_PIC();

            case "OLE": //	Byte[]	DbType.Binary
                return get_OLE();
            case "VBIN": //	Byte[]	DbType.Binary

                return get_VBIN();
            case "D": //	DateTime	DbType.DateTime
                return get_D();
            case "T": //	DateTime	DbType.DateTime
                return get_T();
            case "DT": //	DateTime	DbType.DateTime
                return get_DT();
            case "NO": //	Decimal	DbType.Decimal
                return get_NO();
            case "N": //	Decimal	"DbType.Decimal	"

                return get_N();
            case "DC": //	Decimal	DbType.Decimal
                return get_DC();
            case "LF": //	Double	DbType.Double
                return get_LF();
            case "SI": //	Int16	DbType.Int16
                return get_SI();
            case "I": //	Int32	DbType.Int32
                return get_I();
            case "LI": //	Int64	DbType.Int64
                return get_LI();
            case "MN": //	Single	DbType.Currency
                return get_MN();
            case "A": //	String	DbType.StringFixedLength
                return get_A();
            case "VA": //	String	DbType.String
                return get_VA();
            case "LA": //	String	DbType.String
                return get_LA();
            case "LVA": //	String	DbType.String
                return get_LVA();
            case "TXT": //	String	DbType.String
                return get_TXT();
            case "TS": //	TimeSpan	DbType.Int64
                return get_TS();
            case "F": //	float	DbType.Double
                return get_F();
            case "SF": //	float	DbType.Double
                return get_SF();
            default:
                log.error("未知的字段类型");
                return get_VA();

        }
    }

    /**
     * @param p_field
     * @return
     */
    public MetaType GetMetaType(FieldMeta p_field) {
        return  GetMetaType(p_field.AbstractType());
    }
}



