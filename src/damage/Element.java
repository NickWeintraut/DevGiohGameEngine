package damage;

public enum Element {
	FIRE,
	WATER,
	EARTH,
	WIND, NO_ELEMENT;
	
	public boolean isSuperiorTo(Element e){
		if(this.equals(Element.WATER) && e.equals(Element.FIRE))
			return true;
		else if(this.equals(Element.FIRE) && e.equals(Element.WATER))
			return false;
		
		else if(this.equals(Element.FIRE) && e.equals(Element.WIND))
			return true;
		else if(this.equals(Element.WIND) && e.equals(Element.FIRE))
			return false;
		
		else if(this.equals(Element.WIND) && e.equals(Element.EARTH))
			return true;
		else if(this.equals(Element.EARTH) && e.equals(Element.WIND))
			return false;
		
		else if(this.equals(Element.EARTH) && e.equals(Element.WATER))
			return true;
		else if(this.equals(Element.WATER) && e.equals(Element.EARTH))
			return false;
		
		else
			return false;
		
	}
}
