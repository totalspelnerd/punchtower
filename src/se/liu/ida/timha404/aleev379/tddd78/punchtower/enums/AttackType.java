package se.liu.ida.timha404.aleev379.tddd78.punchtower.enums;

/**
 * This enum keeps track of all of our attack types, with their respective hit chance, crit chance and damage modifiers.
 */
public enum AttackType
{
	/**
	 * Enum for a quick attack
	 */
	 QUICK(1.0f,0.05f,1.0f),
	/**
	 * Enum for a normal attack
	 */
	NORMAL(0.85f,0.1f,1.4f),
	/**
	 * Enum for a heavy attack
	 */
	HEAVY(0.75f,0.15f,2.0f);

	/**
	 * Hit chance for the given enum
	 */
	public final float hitChance;

	/**
	 * Crit chance for the given enum
	 */
	public final float critChance;

	/**
	 * damage modifier for the given enum
	 */
	public final float dmgModifier;

	 private AttackType(float hitChance, float critChance, float dmgModifier)
	 {
		 this.hitChance = hitChance;
		 this.critChance = critChance;
		 this.dmgModifier = dmgModifier;
	 }

 }