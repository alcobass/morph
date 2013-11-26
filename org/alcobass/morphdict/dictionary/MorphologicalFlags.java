package org.alcobass.morphdict.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MorphologicalFlags
{
	final static int MOR_IDX_MIN = 0;
	final static int MOR_IDX_MAX = 52;
	
    // Индексы
	final static int MOR_PS_NOUN = 0; // существительное
	final static int MOR_PS_ADJECTIVE = 1; // прилагательное
	final static int MOR_PS_VERB = 2; // глагол
	final static int MOR_PS_PARTICLE = 3; // частица
	final static int MOR_PS_EXCLAMATION = 4; // междометие
	final static int MOR_PS_CONJUNCTION = 5; // союз
	final static int MOR_PS_ADVERB = 6; // наречие
	final static int MOR_PS_PREPOSITION = 7; // предлог
	final static int MOR_PS_PRONOUN = 8; // местоимение 
	final static int MOR_PS_NUMERAL = 9; // числительное
	final static int MOR_PS_COMPARATIVE = 10; // сравнительная степень
	final static int MOR_PS_PREDICATIVE = 11; // предикатив 
	final static int MOR_PS_PARENTHESIS = 12; // вводное слово
	final static int MOR_PS_UNKNOWN = 13; // хз что

	// Характеристики прилагательных
	final static int MOR_ADJ_SHORT = 14; // краткое
	final static int MOR_ADJ_COMPARATIVE = 15; // сравнительная степень
	
	// Род
	final static int MOR_GEN_MASCULINE = 16;
	final static int MOR_GEN_FEMININE = 17;
	final static int MOR_GEN_NEUTER = 18; // средний
	final static int MOR_GEN_COMMON = 19; // общий
	
	// Число
	final static int MOR_CNT_SINGLE = 20;
	final static int MOR_CNT_PLURAL = 21;
	
	// Одушевленное/неодушевленное
	final static int MOR_ANI_ANIMATE = 22; 
	final static int MOR_ANI_INANIMATE = 23;
	
	// Падеж (case)
	final static int MOR_CASE_NOMINATIVE = 24; // именительный
	final static int MOR_CASE_GENITIVE = 25; // родительный
	final static int MOR_CASE_DATIVE = 26; // дательный
	final static int MOR_CASE_ACCUSATIVE = 27; // винительный
	final static int MOR_CASE_ABLATIVE = 28; // творительный
	final static int MOR_CASE_PREPOSITIONAL = 29; // предложный
	
	// вид глагола
	final static int MOR_ASP_PERFECTIVE = 30; // совершенный
	final static int MOR_ASP_IMPERFECTIVE = 31; // несовершенный
	final static int MOR_ASP_DOUBLE = 32; // двойственный
	
	// тип глагола
	final static int MOR_VERB_PARTICIPLE = 33; // причастие
	final static int MOR_VERB_ADV_PARTICIPLE = 34; // деепричастие

	final static int MOR_VOICE_PASSIVE = 35;
	final static int MOR_VOICE_ACTIVE = 36;
	
	// наклонение глагола
	final static int MOR_MOOD_INDICATIVE = 37; // изъявительное
	final static int MOR_MOOD_CONJUNCTIVE = 38; // сослагательное
	final static int MOR_MOOD_IMPERATIVE = 39; // повелительное
	
	// Время
	final static int MOR_TENSE_PRESENT = 40;
	final static int MOR_TENSE_PAST = 41;
	final static int MOR_TENSE_FUTURE = 42;
	
	// Лицо
	final static int MOR_PERSON_FIRST = 43;
	final static int MOR_PERSON_SECOND = 44;
	final static int MOR_PERSON_THIRD = 45;
	
	final static int MOR_VERB_TRANSITIVE = 46;
	final static int MOR_VERB_NONTRANSITIVE = 47;
	
	final static int MOR_VERB_REFLEXIVE = 48;
	final static int MOR_VERB_NONREFLEXIVE = 49;
	
	final static int MOR_VERB_INFINITIVE = 50;
	final static int MOR_VERB_PERSONAL = 51;
	final static int MOR_VERB_NONPERSONAL = 52;
	
	public final static int ParArraySize = MOR_IDX_MAX + 1;
    
    
    // Флаги
	// Часть речи
	final static long MF_PS_NOUN = 1L << MOR_PS_NOUN; // существительное
	final static long MF_PS_ADJECTIVE = 1L << MOR_PS_ADJECTIVE; // прилагательное
	final static long MF_PS_VERB = 1L << MOR_PS_VERB; // глагол
	final static long MF_PS_PARTICLE = 1L << MOR_PS_PARTICLE; // частица
	final static long MF_PS_EXCLAMATION = 1L << MOR_PS_EXCLAMATION; // междометие
	final static long MF_PS_CONJUNCTION = 1L << MOR_PS_CONJUNCTION; // союз
	final static long MF_PS_ADVERB = 1L << MOR_PS_ADVERB; // наречие
	final static long MF_PS_PREPOSITION = 1L << MOR_PS_PREPOSITION; // предлог
	final static long MF_PS_PRONOUN = 1L << MOR_PS_PRONOUN; // местоимение 
	final static long MF_PS_NUMERAL = 1L << MOR_PS_NUMERAL; // числительное
	final static long MF_PS_COMPARATIVE = 1L << MOR_PS_COMPARATIVE; // сравнительная степень
	final static long MF_PS_PREDICATIVE = 1L << MOR_PS_PREDICATIVE; // предикатив 
	final static long MF_PS_PARENTHESIS = 1L << MOR_PS_PARENTHESIS; // вводное слово
	final static long MF_PS_UNKNOWN = 1L << MOR_PS_UNKNOWN; // хз что

	// Характеристики прилагательных
	final static long MF_ADJ_SHORT = 1L << MOR_ADJ_SHORT; // краткое
	final static long MF_ADJ_COMPARATIVE = 1L << MOR_ADJ_COMPARATIVE; // сравнительная степень
	
	// Род
	final static long MF_GEN_MASCULINE = 1L << MOR_GEN_MASCULINE;
	final static long MF_GEN_FEMININE = 1L << MOR_GEN_FEMININE;
	final static long MF_GEN_NEUTER = 1L << MOR_GEN_NEUTER; // средний
	final static long MF_GEN_COMMON = 1L << MOR_GEN_COMMON; // общий
	
	// Число
	final static long MF_CNT_SINGLE = 1L << MOR_CNT_SINGLE;
	final static long MF_CNT_PLURAL = 1L << MOR_CNT_PLURAL;
	
	// Одушевленное/неодушевленное
	final static long MF_ANI_ANIMATE = 1L << MOR_ANI_ANIMATE; 
	final static long MF_ANI_INANIMATE = 1L << MOR_ANI_INANIMATE;
	
	// Падеж (case)
	final static long MF_CASE_NOMINATIVE = 1L << MOR_CASE_NOMINATIVE; // именительный
	final static long MF_CASE_GENITIVE = 1L << MOR_CASE_GENITIVE; // родительный
	final static long MF_CASE_DATIVE = 1L << MOR_CASE_DATIVE; // дательный
	final static long MF_CASE_ACCUSATIVE = 1L << MOR_CASE_ACCUSATIVE; // винительный
	final static long MF_CASE_ABLATIVE = 1L << MOR_CASE_ABLATIVE; // творительный
	final static long MF_CASE_PREPOSITIONAL = 1L << MOR_CASE_PREPOSITIONAL; // предложный
	
	// вид глагола
	final static long MF_ASP_PERFECTIVE = 1L << MOR_ASP_PERFECTIVE; // совершенный
	final static long MF_ASP_IMPERFECTIVE = 1L << MOR_ASP_IMPERFECTIVE; // несовершенный
	final static long MF_ASP_DOUBLE = 1L << MOR_ASP_DOUBLE; // двойственный
	
	// тип глагола
	final static long MF_VERB_PARTICIPLE = 1L << MOR_VERB_PARTICIPLE; // причастие
	final static long MF_VERB_ADV_PARTICIPLE = 1L << MOR_VERB_ADV_PARTICIPLE; // деепричастие

	final static long MF_VOICE_PASSIVE = 1L << MOR_VOICE_PASSIVE;
	final static long MF_VOICE_ACTIVE = 1L << MOR_VOICE_ACTIVE;
	
	// наклонение глагола
	final static long MF_MOOD_INDICATIVE = 1L << MOR_MOOD_INDICATIVE; // изъявительное
	final static long MF_MOOD_CONJUNCTIVE = 1L << MOR_MOOD_CONJUNCTIVE; // сослагательное
	final static long MF_MOOD_IMPERATIVE = 1L << MOR_MOOD_IMPERATIVE; // повелительное
	
	// Время
	final static long MF_TENSE_PRESENT = 1L << MOR_TENSE_PRESENT;
	final static long MF_TENSE_PAST = 1L << MOR_TENSE_PAST;
	final static long MF_TENSE_FUTURE = 1L << MOR_TENSE_FUTURE;
	
	// Лицо
	final static long MF_PERSON_FIRST = 1L << MOR_PERSON_FIRST;
	final static long MF_PERSON_SECOND = 1L << MOR_PERSON_SECOND;
	final static long MF_PERSON_THIRD = 1L << MOR_PERSON_THIRD;
	
	final static long MF_VERB_TRANSITIVE = 1L << MOR_VERB_TRANSITIVE;
	final static long MF_VERB_NONTRANSITIVE = 1L << MOR_VERB_NONTRANSITIVE;
	
	final static long MF_VERB_REFLEXIVE = 1L << MOR_VERB_REFLEXIVE;
	final static long MF_VERB_NONREFLEXIVE = 1L << MOR_VERB_NONREFLEXIVE;
	
	final static long MF_VERB_INFINITIVE = 1L << MOR_VERB_INFINITIVE;
	final static long MF_VERB_PERSONAL = 1L << MOR_VERB_PERSONAL;
	final static long MF_VERB_NONPERSONAL = 1L << MOR_VERB_NONPERSONAL;
	
	public final static HashMap<String, Long> DictToFlag = new HashMap<String, Long>();
			
	static
	{
		DictToFlag.put("902", MF_PS_NOUN);
		DictToFlag.put("903", MF_PS_ADJECTIVE);
		DictToFlag.put("904", MF_PS_VERB);
		DictToFlag.put("905", MF_PS_PARTICLE);
		DictToFlag.put("906", MF_PS_EXCLAMATION);
		DictToFlag.put("907", MF_PS_CONJUNCTION);
		DictToFlag.put("908", MF_PS_ADVERB);
		DictToFlag.put("909", MF_PS_PREPOSITION);
		DictToFlag.put("910", MF_PS_PRONOUN);
		DictToFlag.put("911", MF_PS_NUMERAL);
		DictToFlag.put("912", MF_PS_COMPARATIVE);
		DictToFlag.put("913", MF_PS_PREDICATIVE);
		DictToFlag.put("914", MF_PS_PARENTHESIS);
		DictToFlag.put("915", MF_PS_UNKNOWN);
		DictToFlag.put("916", MF_ADJ_SHORT);
		DictToFlag.put("917", MF_ADJ_COMPARATIVE);
		DictToFlag.put("918", MF_GEN_MASCULINE);
		DictToFlag.put("919", MF_GEN_FEMININE);
		DictToFlag.put("920", MF_GEN_NEUTER);
		DictToFlag.put("921", MF_GEN_COMMON);
		DictToFlag.put("922", MF_CNT_SINGLE);
		DictToFlag.put("923", MF_CNT_PLURAL);
		DictToFlag.put("924", MF_ANI_ANIMATE);
		DictToFlag.put("925", MF_ANI_INANIMATE);
		DictToFlag.put("926", MF_CASE_NOMINATIVE);
		DictToFlag.put("927", MF_CASE_GENITIVE);
		DictToFlag.put("928", MF_CASE_DATIVE);
		DictToFlag.put("929", MF_CASE_ACCUSATIVE);
		DictToFlag.put("930", MF_CASE_ABLATIVE);
		DictToFlag.put("931", MF_CASE_PREPOSITIONAL);
		DictToFlag.put("932", MF_ASP_PERFECTIVE);
		DictToFlag.put("933", MF_ASP_IMPERFECTIVE);
		DictToFlag.put("934", MF_ASP_DOUBLE);
		DictToFlag.put("935", MF_VERB_PARTICIPLE);
		DictToFlag.put("936", MF_VERB_ADV_PARTICIPLE);
		DictToFlag.put("937", MF_VOICE_PASSIVE);
		DictToFlag.put("938", MF_VOICE_ACTIVE);
		DictToFlag.put("939", MF_MOOD_INDICATIVE);
		DictToFlag.put("940", MF_MOOD_CONJUNCTIVE);
		DictToFlag.put("941", MF_MOOD_IMPERATIVE);
		DictToFlag.put("942", MF_TENSE_PAST);
		DictToFlag.put("943", MF_TENSE_PRESENT);
		DictToFlag.put("944", MF_TENSE_FUTURE);
		DictToFlag.put("945", MF_PERSON_FIRST);
		DictToFlag.put("946", MF_PERSON_SECOND);
		DictToFlag.put("947", MF_PERSON_THIRD);
        DictToFlag.put("948", MF_VERB_TRANSITIVE);
        DictToFlag.put("949", MF_VERB_NONTRANSITIVE);
        DictToFlag.put("950", MF_VERB_REFLEXIVE);
        DictToFlag.put("951", MF_VERB_NONREFLEXIVE);
        DictToFlag.put("952", MF_VERB_INFINITIVE);
        DictToFlag.put("953", MF_VERB_PERSONAL);
        DictToFlag.put("954", MF_VERB_NONPERSONAL);
		
		
	}
	
	public final static HashMap<Integer, Long> IdxToFlag = new HashMap<Integer, Long>();
	
	static 
	{
		
		IdxToFlag.put(MOR_PS_NOUN, MF_PS_NOUN);
		IdxToFlag.put(MOR_PS_ADJECTIVE, MF_PS_ADJECTIVE);
		IdxToFlag.put(MOR_PS_VERB, MF_PS_VERB);
		IdxToFlag.put(MOR_PS_PARTICLE, MF_PS_PARTICLE);
		IdxToFlag.put(MOR_PS_EXCLAMATION, MF_PS_EXCLAMATION);
		IdxToFlag.put(MOR_PS_CONJUNCTION, MF_PS_CONJUNCTION);
		IdxToFlag.put(MOR_PS_ADVERB, MF_PS_ADVERB);
		IdxToFlag.put(MOR_PS_PREPOSITION, MF_PS_PREPOSITION);
		IdxToFlag.put(MOR_PS_PRONOUN, MF_PS_PRONOUN);
		IdxToFlag.put(MOR_PS_NUMERAL, MF_PS_NUMERAL);
		IdxToFlag.put(MOR_PS_COMPARATIVE, MF_PS_COMPARATIVE);
		IdxToFlag.put(MOR_PS_PREDICATIVE, MF_PS_PREDICATIVE);
		IdxToFlag.put(MOR_PS_PARENTHESIS, MF_PS_PARENTHESIS);
		IdxToFlag.put(MOR_PS_UNKNOWN, MF_PS_UNKNOWN);
		IdxToFlag.put(MOR_ADJ_SHORT, MF_ADJ_SHORT);
		IdxToFlag.put(MOR_ADJ_COMPARATIVE, MF_ADJ_COMPARATIVE);
		IdxToFlag.put(MOR_GEN_MASCULINE, MF_GEN_MASCULINE);
		IdxToFlag.put(MOR_GEN_FEMININE, MF_GEN_FEMININE);
		IdxToFlag.put(MOR_GEN_NEUTER, MF_GEN_NEUTER);
		IdxToFlag.put(MOR_GEN_COMMON, MF_GEN_COMMON);
		IdxToFlag.put(MOR_CNT_SINGLE, MF_CNT_SINGLE);
		IdxToFlag.put(MOR_CNT_PLURAL, MF_CNT_PLURAL);
		IdxToFlag.put(MOR_ANI_ANIMATE, MF_ANI_ANIMATE);
		IdxToFlag.put(MOR_ANI_INANIMATE, MF_ANI_INANIMATE);
		IdxToFlag.put(MOR_CASE_NOMINATIVE, MF_CASE_NOMINATIVE);
		IdxToFlag.put(MOR_CASE_GENITIVE, MF_CASE_GENITIVE);
		IdxToFlag.put(MOR_CASE_DATIVE, MF_CASE_DATIVE);
		IdxToFlag.put(MOR_CASE_ACCUSATIVE, MF_CASE_ACCUSATIVE);
		IdxToFlag.put(MOR_CASE_ABLATIVE, MF_CASE_ABLATIVE);
		IdxToFlag.put(MOR_CASE_PREPOSITIONAL, MF_CASE_PREPOSITIONAL);
		IdxToFlag.put(MOR_ASP_PERFECTIVE, MF_ASP_PERFECTIVE);
		IdxToFlag.put(MOR_ASP_IMPERFECTIVE, MF_ASP_IMPERFECTIVE);
		IdxToFlag.put(MOR_ASP_DOUBLE, MF_ASP_DOUBLE);
		IdxToFlag.put(MOR_VERB_PARTICIPLE, MF_VERB_PARTICIPLE);
		IdxToFlag.put(MOR_VERB_ADV_PARTICIPLE, MF_VERB_ADV_PARTICIPLE);
		IdxToFlag.put(MOR_VOICE_PASSIVE, MF_VOICE_PASSIVE);
		IdxToFlag.put(MOR_VOICE_ACTIVE, MF_VOICE_ACTIVE);
		IdxToFlag.put(MOR_MOOD_INDICATIVE, MF_MOOD_INDICATIVE);
		IdxToFlag.put(MOR_MOOD_CONJUNCTIVE, MF_MOOD_CONJUNCTIVE);
		IdxToFlag.put(MOR_MOOD_IMPERATIVE, MF_MOOD_IMPERATIVE);
		IdxToFlag.put(MOR_TENSE_PAST, MF_TENSE_PAST);
		IdxToFlag.put(MOR_TENSE_PRESENT, MF_TENSE_PRESENT);
		IdxToFlag.put(MOR_TENSE_FUTURE, MF_TENSE_FUTURE);
		IdxToFlag.put(MOR_PERSON_FIRST, MF_PERSON_FIRST);
		IdxToFlag.put(MOR_PERSON_SECOND, MF_PERSON_SECOND);
		IdxToFlag.put(MOR_PERSON_THIRD, MF_PERSON_THIRD);
	    IdxToFlag.put(MOR_VERB_TRANSITIVE, MF_VERB_TRANSITIVE);
	    IdxToFlag.put(MOR_VERB_NONTRANSITIVE, MF_VERB_NONTRANSITIVE);
	    IdxToFlag.put(MOR_VERB_REFLEXIVE, MF_VERB_REFLEXIVE);
	    IdxToFlag.put(MOR_VERB_NONREFLEXIVE, MF_VERB_NONREFLEXIVE);
	    IdxToFlag.put(MOR_VERB_INFINITIVE, MF_VERB_INFINITIVE);
	    IdxToFlag.put(MOR_VERB_PERSONAL, MF_VERB_PERSONAL);
	    IdxToFlag.put(MOR_VERB_NONPERSONAL, MF_VERB_NONPERSONAL);
	}
	
	public final static HashMap<Long, Integer> FlagToIdx = new HashMap<Long, Integer>();
	
    static
    {
		FlagToIdx.put(MF_PS_NOUN, MOR_PS_NOUN);
		FlagToIdx.put(MF_PS_ADJECTIVE, MOR_PS_ADJECTIVE);
		FlagToIdx.put(MF_PS_VERB, MOR_PS_VERB);
		FlagToIdx.put(MF_PS_PARTICLE, MOR_PS_PARTICLE);
		FlagToIdx.put(MF_PS_EXCLAMATION, MOR_PS_EXCLAMATION);
		FlagToIdx.put(MF_PS_CONJUNCTION, MOR_PS_CONJUNCTION);
		FlagToIdx.put(MF_PS_ADVERB, MOR_PS_ADVERB);
		FlagToIdx.put(MF_PS_PREPOSITION, MOR_PS_PREPOSITION);
		FlagToIdx.put(MF_PS_PRONOUN, MOR_PS_PRONOUN);
		FlagToIdx.put(MF_PS_NUMERAL, MOR_PS_NUMERAL);
		FlagToIdx.put(MF_PS_COMPARATIVE, MOR_PS_COMPARATIVE);
		FlagToIdx.put(MF_PS_PREDICATIVE, MOR_PS_PREDICATIVE);
		FlagToIdx.put(MF_PS_PARENTHESIS, MOR_PS_PARENTHESIS);
		FlagToIdx.put(MF_PS_UNKNOWN, MOR_PS_UNKNOWN);
		FlagToIdx.put(MF_ADJ_SHORT, MOR_ADJ_SHORT);
		FlagToIdx.put(MF_ADJ_COMPARATIVE, MOR_ADJ_COMPARATIVE);
		FlagToIdx.put(MF_GEN_MASCULINE, MOR_GEN_MASCULINE);
		FlagToIdx.put(MF_GEN_FEMININE, MOR_GEN_FEMININE);
		FlagToIdx.put(MF_GEN_NEUTER, MOR_GEN_NEUTER);
		FlagToIdx.put(MF_GEN_COMMON, MOR_GEN_COMMON);
		FlagToIdx.put(MF_CNT_SINGLE, MOR_CNT_SINGLE);
		FlagToIdx.put(MF_CNT_PLURAL, MOR_CNT_PLURAL);
		FlagToIdx.put(MF_ANI_ANIMATE, MOR_ANI_ANIMATE);
		FlagToIdx.put(MF_ANI_INANIMATE, MOR_ANI_INANIMATE);
		FlagToIdx.put(MF_CASE_NOMINATIVE, MOR_CASE_NOMINATIVE);
		FlagToIdx.put(MF_CASE_GENITIVE, MOR_CASE_GENITIVE);
		FlagToIdx.put(MF_CASE_DATIVE, MOR_CASE_DATIVE);
		FlagToIdx.put(MF_CASE_ACCUSATIVE, MOR_CASE_ACCUSATIVE);
		FlagToIdx.put(MF_CASE_ABLATIVE, MOR_CASE_ABLATIVE);
		FlagToIdx.put(MF_CASE_PREPOSITIONAL, MOR_CASE_PREPOSITIONAL);
		FlagToIdx.put(MF_ASP_PERFECTIVE, MOR_ASP_PERFECTIVE);
		FlagToIdx.put(MF_ASP_IMPERFECTIVE, MOR_ASP_IMPERFECTIVE);
		FlagToIdx.put(MF_ASP_DOUBLE, MOR_ASP_DOUBLE);
		FlagToIdx.put(MF_VERB_PARTICIPLE, MOR_VERB_PARTICIPLE);
		FlagToIdx.put(MF_VERB_ADV_PARTICIPLE, MOR_VERB_ADV_PARTICIPLE);
		FlagToIdx.put(MF_VOICE_PASSIVE, MOR_VOICE_PASSIVE);
		FlagToIdx.put(MF_VOICE_ACTIVE, MOR_VOICE_ACTIVE);
		FlagToIdx.put(MF_MOOD_INDICATIVE, MOR_MOOD_INDICATIVE);
		FlagToIdx.put(MF_MOOD_CONJUNCTIVE, MOR_MOOD_CONJUNCTIVE);
		FlagToIdx.put(MF_MOOD_IMPERATIVE, MOR_MOOD_IMPERATIVE);
		FlagToIdx.put(MF_TENSE_PAST, MOR_TENSE_PAST);
		FlagToIdx.put(MF_TENSE_PRESENT, MOR_TENSE_PRESENT);
		FlagToIdx.put(MF_TENSE_FUTURE, MOR_TENSE_FUTURE);
		FlagToIdx.put(MF_PERSON_FIRST, MOR_PERSON_FIRST);
		FlagToIdx.put(MF_PERSON_SECOND, MOR_PERSON_SECOND);
		FlagToIdx.put(MF_PERSON_THIRD, MOR_PERSON_THIRD);
	    FlagToIdx.put(MF_VERB_TRANSITIVE, MOR_VERB_TRANSITIVE);
	    FlagToIdx.put(MF_VERB_NONTRANSITIVE, MOR_VERB_NONTRANSITIVE);
	    FlagToIdx.put(MF_VERB_REFLEXIVE, MOR_VERB_REFLEXIVE);
	    FlagToIdx.put(MF_VERB_NONREFLEXIVE, MOR_VERB_NONREFLEXIVE);
	    FlagToIdx.put(MF_VERB_INFINITIVE, MOR_VERB_INFINITIVE);
	    FlagToIdx.put(MF_VERB_PERSONAL, MOR_VERB_PERSONAL);
	    FlagToIdx.put(MF_VERB_NONPERSONAL, MOR_VERB_NONPERSONAL);

    }
    
    public static List<Integer> getIndexesForFlagSet(Long flags)
    {
    	List<Integer> res = new ArrayList<Integer>();
    	for (int i = MOR_IDX_MIN; i<= MOR_IDX_MAX; i++)
    	{
    		if ((IdxToFlag.get(i) & flags) != 0)
    			res.add(i);
    	}
    	
    	return res;
    	
    }
}
