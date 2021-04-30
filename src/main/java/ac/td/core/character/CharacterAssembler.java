package ac.td.core.character;

import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

import java.util.*;

public class CharacterAssembler {
    private boolean alreadyBuilt = false;

    private final BaseCharacter baseCharacter;
    private final BaseCharacter assemblingCharacter;

    private CategoryType[] attributesPriorities = { CategoryType.PHYSICAL, CategoryType.MENTAL, CategoryType.SOCIAL };
    private final int[] attributesCategoriesMaxPoints = { 5, 4, 3 };

    private CategoryType[] skillsPriorities = { CategoryType.PHYSICAL, CategoryType.MENTAL, CategoryType.SOCIAL };
    private final int[] skillsCategoriesMaxPoints = { 11, 7, 4 };

    private CharacterAssembler(final BaseCharacter character) {
        this.baseCharacter = new BaseCharacter(0);
        this.assemblingCharacter = character;
    }

    public static CharacterAssembler createBeginnerAssembler() {
        return new CharacterAssembler(new BaseCharacter(LevelType.BEGINNER.getInitialXp()));
    }

    public static CharacterAssembler createExperiencedAssembler() {
        return new CharacterAssembler(new BaseCharacter(LevelType.EXPERIENCED.getInitialXp()));
    }

    public static CharacterAssembler createSpecialistAssembler() {
        return new CharacterAssembler(new BaseCharacter(LevelType.SPECIALIST.getInitialXp()));
    }

    public static CharacterAssembler createHeroicAssembler() {
        return new CharacterAssembler(new BaseCharacter(LevelType.HEROIC.getInitialXp()));
    }

    public CharacterAssembler setConcept(final String concept) {
        this.baseCharacter.concept = concept;
        return this;
    }

    public CharacterAssembler setAttributesPriorities(
            final CategoryType first,
            final CategoryType second,
            final CategoryType third)
            throws CharacterPriorityException {
        this.attributesPriorities = this.getValidatedPriorities(first, second, third);
        return this;
    }

    public CharacterAssembler setSkillsPriorities(
            final CategoryType first,
            final CategoryType second,
            final CategoryType third)
            throws CharacterPriorityException {
        this.skillsPriorities = this.getValidatedPriorities(first, second, third);
        return this;
    }

    private CategoryType[] getValidatedPriorities(
            final CategoryType first,
            final CategoryType second,
            final CategoryType third)
            throws CharacterPriorityException {
        if (Set.of(first, second, third).size() < 3) {
            throw new CharacterPriorityException("All categories must be distinct");
        }

        return new CategoryType[] { first, second, third };
    }

    public CharacterAssembler setMentalAttributes(final int intelligence, final int wits, final int resolve)
            throws TooManyDotsPerCategoryException {
        this.setLevels(
                CategoryType.MENTAL,
                this.attributesPriorities,
                this.attributesCategoriesMaxPoints,
                Map.entry(AttributeType.INTELLIGENCE.name(), intelligence),
                Map.entry(AttributeType.WITS.name(), wits),
                Map.entry(AttributeType.RESOLVE.name(), resolve));

        return this;
    }

    public CharacterAssembler setPhysicalAttributes(final int strength, final int dexterity, final int stamina)
            throws TooManyDotsPerCategoryException {
        this.setLevels(
                CategoryType.PHYSICAL,
                this.attributesPriorities,
                this.attributesCategoriesMaxPoints,
                Map.entry(AttributeType.STRENGTH.name(), strength),
                Map.entry(AttributeType.DEXTERITY.name(), dexterity),
                Map.entry(AttributeType.STAMINA.name(), stamina));

        return this;
    }

    public CharacterAssembler setSocialAttributes(final int presence, final int manipulation, final int composure)
            throws TooManyDotsPerCategoryException {
        this.setLevels(
                CategoryType.SOCIAL,
                this.attributesPriorities,
                this.attributesCategoriesMaxPoints,
                Map.entry(AttributeType.PRESENCE.name(), presence),
                Map.entry(AttributeType.MANIPULATION.name(), manipulation),
                Map.entry(AttributeType.COMPOSURE.name(), composure));

        return this;
    }

    public CharacterAssembler setMentalSkills(
            final int academics,
            final int computer,
            final int crafts,
            final int investigation,
            final int medicine,
            final int occult,
            final int politics,
            final int science)
            throws TooManyDotsPerCategoryException {
        this.setLevels(
                CategoryType.MENTAL,
                this.skillsPriorities,
                this.skillsCategoriesMaxPoints,
                Map.entry(SkillType.ACADEMICS.name(), academics),
                Map.entry(SkillType.COMPUTER.name(), computer),
                Map.entry(SkillType.CRAFTS.name(), crafts),
                Map.entry(SkillType.INVESTIGATION.name(), investigation),
                Map.entry(SkillType.MEDICINE.name(), medicine),
                Map.entry(SkillType.OCCULT.name(), occult),
                Map.entry(SkillType.POLITICS.name(), politics),
                Map.entry(SkillType.SCIENCE.name(), science));

        return this;
    }

    public CharacterAssembler setPhysicalSkills(
            final int athletics,
            final int brawl,
            final int drive,
            final int firearms,
            final int larceny,
            final int stealth,
            final int survival,
            final int weaponry)
            throws TooManyDotsPerCategoryException {
        this.setLevels(
                CategoryType.PHYSICAL,
                this.skillsPriorities,
                this.skillsCategoriesMaxPoints,
                Map.entry(SkillType.ATHLETICS.name(), athletics),
                Map.entry(SkillType.BRAWL.name(), brawl),
                Map.entry(SkillType.DRIVE.name(), drive),
                Map.entry(SkillType.FIREARMS.name(), firearms),
                Map.entry(SkillType.LARCENY.name(), larceny),
                Map.entry(SkillType.STEALTH.name(), stealth),
                Map.entry(SkillType.SURVIVAL.name(), survival),
                Map.entry(SkillType.WEAPONRY.name(), weaponry));

        return this;
    }

    public CharacterAssembler setSocialSkills(
            final int animalKen,
            final int empathy,
            final int expression,
            final int intimidation,
            final int persuasion,
            final int socialize,
            final int streetwise,
            final int subterfuge)
            throws TooManyDotsPerCategoryException {
        this.setLevels(
                CategoryType.SOCIAL,
                this.skillsPriorities,
                this.skillsCategoriesMaxPoints,
                Map.entry(SkillType.ANIMAL_KEN.name(), animalKen),
                Map.entry(SkillType.EMPATHY.name(), empathy),
                Map.entry(SkillType.EXPRESSION.name(), expression),
                Map.entry(SkillType.INTIMIDATION.name(), intimidation),
                Map.entry(SkillType.PERSUASION.name(), persuasion),
                Map.entry(SkillType.SOCIALIZE.name(), socialize),
                Map.entry(SkillType.STREETWISE.name(), streetwise),
                Map.entry(SkillType.SUBTERFUGE.name(), subterfuge));

        return this;
    }

    @SafeVarargs
    private void setLevels(
            final CategoryType categoryType,
            final CategoryType[] priorities,
            final int[] categoriesMaxPoints,
            final Map.Entry<String, Integer>... entries)
            throws TooManyDotsPerCategoryException {
        final int idxPriority = Arrays.asList(priorities).indexOf(categoryType);
        final int maxPoints = categoriesMaxPoints[idxPriority];

        final int total = Arrays.stream(entries)
                .mapToInt(Map.Entry::getValue)
                .reduce(0, Integer::sum);

        if (total > maxPoints) {
            throw new TooManyDotsPerCategoryException(
                    String.format(
                            "Exceeded number of points for this category %s: %d of %d",
                            categoryType.name(),
                            total,
                            maxPoints));
        }

        for (Map.Entry<String, Integer> entry : entries) {
            this.validateMaxValue(entry.getKey(), entry.getValue());
            this.baseCharacter.traits.put(
                    entry.getKey(),
                    this.assemblingCharacter.getLevel(entry.getKey()) + entry.getValue());
        }
    }

    private void validateMaxValue(final String attributeOrSkill, final int level) {
        int newLevel = this.assemblingCharacter.getLevel(attributeOrSkill) + level;
        if (newLevel > 5) {
            throw new RuntimeException("Cannot exceed level 5");
        }
    }

    public CharacterAssembler setVirtue(final VirtueType virtue) {
        this.baseCharacter.virtue = virtue;
        return this;
    }

    public CharacterAssembler setVice(final ViceType vice) {
        this.baseCharacter.vice = vice;
        return this;
    }

    public CharacterAssembler setSpecialties(
            final SpecialtyType first,
            final SpecialtyType second,
            final SpecialtyType third)
            throws CharacterSpecialtyException {
        if (Set.of(first, second, third).size() < 3) {
            throw new CharacterSpecialtyException("All specialties must be distinct");
        }

        this.baseCharacter.specialties.clear();
        this.baseCharacter.specialties.addAll(Set.of(first, second, third));
        return this;
    }

    private void setMerits(final MeritType merit, final int value) throws TooManyDotsOnMeritException {
        throw new TooManyDotsOnMeritException("Not Implemented yet");
    }

    public BaseCharacter assemble()
            throws NoVirtueException,
            NoViceException,
            CharacterAlreadyBuiltException,
            NoConceptException,
            CharacterSpecialtyException {
        if (this.alreadyBuilt) {
            throw new CharacterAlreadyBuiltException("Cannot reuse builder");
        }

        if (Objects.isNull(this.baseCharacter.concept)) {
            throw new NoConceptException("Must set a concept");
        }

        if (Objects.isNull(this.baseCharacter.virtue)) {
            throw new NoVirtueException("Must set a virtue");
        }

        if (Objects.isNull(this.baseCharacter.vice)) {
            throw new NoViceException("Must set a vice");
        }

        if (this.baseCharacter.specialties.size() < 3) {
            throw new CharacterSpecialtyException("Must set three specialties");
        }

        final int spentXp = this.calculateSpentXp();
        this.copyBasicCharacteristics(this.baseCharacter, this.assemblingCharacter);
        this.assemblingCharacter.experiencePoints -= spentXp;

        this.alreadyBuilt = true;
        return this.assemblingCharacter;
    }

    private int calculateSingleSpentXp(final int previousLevel, final int newLevel) {
        if (newLevel == previousLevel) {
            return 0;
        }

        if (previousLevel >= 5) {
            return (newLevel - previousLevel) * 2;
        }

        if (newLevel >= 5) {
            int xpSinglePoints = previousLevel == 4 ? 0 : 4 - previousLevel;
            return xpSinglePoints + (2 * (newLevel - 4));
        }

        return newLevel - previousLevel;
    }

    public int calculateSpentXp() {
        return this.baseCharacter.traits.entrySet().stream().mapToInt(
                entry -> calculateSingleSpentXp(
                        this.assemblingCharacter.traits.get(entry.getKey()),
                        entry.getValue()))
                .sum();
    }

    private void copyBasicCharacteristics(final BaseCharacter original, final BaseCharacter copy) {
        copy.concept = original.concept;
        copy.virtue = original.virtue;
        copy.vice = original.vice;
        copy.traits.putAll(original.traits);
    }
}
