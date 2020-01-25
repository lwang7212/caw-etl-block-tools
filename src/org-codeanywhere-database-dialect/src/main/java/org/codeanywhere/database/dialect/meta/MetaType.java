package org.codeanywhere.database.dialect.meta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class MetaType
{
	
    public static String BL = "BL";

    public static String BT = "BT";

    
    public static String MBT= "MBT";

    
    public static String VMBT= "VMBT";

   
    public static String BIN= "BIN";

   
    public static String LBIN= "LBIN";

   
    public static String BMP= "BMP";

   
    public static String PIC= "PIC";

   
    public static String OLE= "OLE";

    
    public static String VBIN  = "VBIN";

   
    public static String D= "D";

  
    public static String T= "T";

   
    public static String DT= "DT";

    
    public static String NO= "NO";

    
    public static String N= "N";

   
    public static String DC= "DC";

   
    public static String LF= "LF";

   
    public static String SI= "SI";

    
    public static String I= "I";

   
    public static String LI= "LI";

  
    public static String MN  = "MN";

   
    public static String A= "A";

    
    public static String VA= "VA";

   
    public static String LA= "LA";

    
    public static String LVA= "LVA";

    
    public static String TXT= "TXT";

    
    public static String TS= "TS";

  
    public static String F= "F";

   
    public static String SF= "SF";
    /**
    /* 基础类型ID
    */   
   private String AbstractTypeID;

  

    /**
    /* 类类型
    */
   private String columnType;

    /**
    /* 列类型模板   $l $p $s
    */
   private String columnDefineTemplate;

    /**
    /* 默认长度
    */
   private int defaultLength;

    /**
    /* 默认位数
    */
   private int defaultPrec;

    /**
	 * @return the abstractTypeID
	 */
	public String getAbstractTypeID()
	{
		return AbstractTypeID;
	}

	/**
	 * @return the columnType
	 */
	public String getColumnType()
	{
		return columnType;
	}

	/**
	 * @return the columnDefineTemplate
	 */
	public String getColumnDefineTemplate()
	{
		return columnDefineTemplate;
	}

	/**
	 * @return the defaultLength
	 */
	public int getDefaultLength()
	{
		return defaultLength;
	}

	/**
	 * @return the defaultPrec
	 */
	public int getDefaultPrec()
	{
		return defaultPrec;
	}

	/**
	 * @return the defaultScale
	 */
	public int getDefaultScale()
	{
		return defaultScale;
	}

	/**
	 * @return the isfixed
	 */
	public boolean getIsfixed()
	{
		return isfixed;
	}

	/**
	 * @return the fixedLength
	 */
	public int getFixedLength()
	{
		return fixedLength;
	}

	/**
	 * @param abstractTypeID the abstractTypeID to set
	 */
	public MetaType setAbstractTypeID(String abstractTypeID)
	{
		AbstractTypeID = abstractTypeID;
		return this;
	}

	/**
	 * @param columnType the columnType to set
	 */
	public MetaType setColumnType(String columnType)
	{
		this.columnType = columnType;
		return this;
	}

	/**
	 * @param columnDefineTemplate the columnDefineTemplate to set
	 */
	public MetaType setColumnDefineTemplate(String columnDefineTemplate)
	{
		this.columnDefineTemplate = columnDefineTemplate;
		return this;
	}

	/**
	 * @param defaultLength the defaultLength to set
	 */
	public MetaType setDefaultLength(int defaultLength)
	{
		this.defaultLength = defaultLength;
		return this;
	}

	/**
	 * @param defaultPrec the defaultPrec to set
	 */
	public MetaType setDefaultPrec(int defaultPrec)
	{
		this.defaultPrec = defaultPrec;
		return this;
	}

	/**
	 * @param defaultScale the defaultScale to set
	 */
	public MetaType setDefaultScale(int defaultScale)
	{
		this.defaultScale = defaultScale;
		return this;
	}

	/**
	 * @param isfixed the isfixed to set
	 */
	public MetaType setIsfixed(boolean isfixed)
	{
		this.isfixed = isfixed;
		return this;
	}

	/**
	 * @param fixedLength the fixedLength to set
	 */
	public MetaType setFixedLength(int fixedLength)
	{
		this.fixedLength = fixedLength;
		return this;
	}

	/**
    /* 小数位
    */
   private int defaultScale;

    /**
    /* 是否固定
    */
   private boolean isfixed;

    /**
    /* 固定长度
    */
   private int fixedLength;
   /**
    * 自动解析数据类型
    * @param oValue
    * @return
    */
   public static String  parser(Object  oValue)
   {
	   if (oValue  instanceof String  )
	   {
		   if(((String)oValue).length()>4000)
		   {
			   return MetaType.TXT;
		   }
		   else
		   {
			   return MetaType.VA;
		   }
	   }
	   if (oValue  instanceof Date  )
	   {
		  
			   return MetaType.DT;
		  
	   }
	   if (oValue  instanceof Integer  )
	   {
		  
			   return MetaType.I;
		  
	   }
	   if (oValue  instanceof Float  )
	   {
		  
			   return MetaType.F;
		  
	   }
	   if (oValue  instanceof Double  )
	   {
		  
			   return MetaType.LF;
		  
	   }
	   if (oValue  instanceof Long  )
	   {
		  
			   return MetaType.LI;
		  
	   }
	   if (oValue  instanceof Byte  )
	   {
		  
			   return MetaType.BT;
		  
	   }
	   if (oValue  instanceof Boolean  )
	   {
		  
			   return MetaType.BL;
		  
	   }
	   if (oValue  instanceof Number  )
	   {
		  
			   return MetaType.DC;
		  
	   }
	   if (oValue  instanceof BigDecimal  )
	   {
		  
			   return MetaType.DC;
		  
	   }
	   if (oValue  instanceof BigDecimal  )
	   {
		  
			   return MetaType.DC;
		  
	   }
	   if (oValue  instanceof BigInteger  )
	   {
		  
			   return MetaType.LI;
		  
	   }
	   
	   return null;
   }
}
