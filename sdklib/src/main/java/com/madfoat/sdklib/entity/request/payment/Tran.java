package com.madfoat.sdklib.entity.request.payment;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hany on 30/04/17.
 */

@Root(strict = false)
public class Tran implements Parcelable
{

	public static final Creator<Tran> CREATOR = new Creator<Tran>()
	{
		@Override
		public Tran createFromParcel(Parcel source)
		{
			return new Tran(source);
		}

		@Override
		public Tran[] newArray(int size)
		{
			return new Tran[size];
		}
	};
	@Element
	private String test;
	@Element
	private String type;
	@Element(name = "class", type = String.class)
	private String clazz;
	@Element
	private String cartid;
	@Element
	private String description;
	@Element
	private String currency;
	@Element
	private String amount;
	@Element
	private String language;
	@Element(required = false)
	private String ref;
	@Element
	private String version;
	@Element(required = false)
	private String firstref;

     public String getLanguage()
	  {
		  return language;
	  }

	  public void setLanguage(String language)
	  {
		  this.language = language;
	  }

	public Tran()
	{
	}

	protected Tran(Parcel in)
	{
		this.test = in.readString();
		this.type = in.readString();
		this.clazz = in.readString();
		this.cartid = in.readString();
		this.description = in.readString();
		this.currency = in.readString();
		this.amount = in.readString();
		this.language = in.readString();
		this.ref = in.readString();
		this.version = in.readString();
		this.firstref = in.readString();
	}

	public String getRef()
	{

		return ref;
	}

	public void setRef(String ref)
	{
		this.ref = ref;
	}

	public String getTest()
	{
		return test;
	}

	public void setTest(String test)
	{
		this.test = test;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}

	public String getCartid()
	{
		return cartid;
	}

	public void setCartid(String cartid)
	{
		this.cartid = cartid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getAmount()
	{
		return amount;
	}

	public void setAmount(String amount)
	{
		this.amount = amount;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getFirstref()
	{
		return firstref;
	}

	public void setFirstref(String firstref)
	{
		this.firstref = firstref;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(this.test);
		dest.writeString(this.type);
		dest.writeString(this.clazz);
		dest.writeString(this.cartid);
		dest.writeString(this.description);
		dest.writeString(this.currency);
		dest.writeString(this.amount);
		dest.writeString(this.language);
		dest.writeString(this.ref);
		dest.writeString(this.version);
		dest.writeString(this.firstref);
	}

    @Override
    public String toString()
    {
        return "Tran{" +
                "test='" + test + '\'' +
                ", type='" + type + '\'' +
                ", clazz='" + clazz + '\'' +
                ", cartid='" + cartid + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", language='" + language + '\'' +
                ", ref='" + ref + '\'' +
                ", version='" + version + '\'' +
                ", firstref='" + firstref + '\'' +
                '}';
    }
}
