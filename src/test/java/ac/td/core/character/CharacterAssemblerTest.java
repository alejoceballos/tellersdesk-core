package ac.td.core.character;

import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterAssemblerTest {

    @Test
    public void buildBeginner() throws CharacterException {
        final CharacterAssembler builder = CharacterAssembler.createBeginnerAssembler()
                .setConcept("Character's concept")
                .setVirtue(VirtueType.FAITH)
                .setVice(ViceType.GREED)
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3);
        Assertions.assertEquals(builder.calculateSpentXp(), 0);

        BaseCharacter beginner = builder.assemble();
        Assertions.assertEquals(beginner.getExperiencePoints(), 34);

        for (final AttributeType attributeType : AttributeType.values()) {
            Assertions.assertEquals(1, beginner.getAttribute(attributeType));
        }
        for (final SkillType skillType : SkillType.values()) {
            Assertions.assertEquals(0, beginner.getSkill(skillType));
        }
    }

    @Test
    public void buildExperienced() throws CharacterException {
        final CharacterAssembler builder = CharacterAssembler.createExperiencedAssembler()
                .setConcept("Character's concept")
                .setVirtue(VirtueType.FAITH)
                .setVice(ViceType.GREED)
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3);
        Assertions.assertEquals(builder.calculateSpentXp(), 0);
        Assertions.assertEquals(builder.assemble().getExperiencePoints(), 69);
    }

    @Test
    public void buildSpecialist() throws CharacterException {
        final CharacterAssembler builder = CharacterAssembler.createSpecialistAssembler()
                .setConcept("Character's concept")
                .setVirtue(VirtueType.FAITH)
                .setVice(ViceType.GREED)
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3);
        Assertions.assertEquals(builder.calculateSpentXp(), 0);
        Assertions.assertEquals(builder.assemble().getExperiencePoints(), 109);
    }

    @Test
    public void buildHeroic() throws CharacterException {
        final CharacterAssembler builder = CharacterAssembler.createHeroicAssembler()
                .setConcept("Character's concept")
                .setVirtue(VirtueType.FAITH)
                .setVice(ViceType.GREED)
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3);
        Assertions.assertEquals(builder.calculateSpentXp(), 0);
        Assertions.assertEquals(builder.assemble().getExperiencePoints(), 134);
    }

    @Test
    public void buildCannotBeReused() throws CharacterException {
        CharacterAssembler assembler = CharacterAssembler.createBeginnerAssembler()
                .setConcept("Character's concept")
                .setVirtue(VirtueType.FAITH)
                .setVice(ViceType.GREED)
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3);
        assembler.assemble();
        Assertions.assertThrows(CharacterAlreadyBuiltException.class, assembler::assemble);
    }


    @Test
    public void buildBeginnerCharacter_AllTraits() throws CharacterException {
        final CharacterAssembler assembler = CharacterAssembler.createBeginnerAssembler()
                // Step One: Character Concept
                .setConcept("Character's concept")

                // Step Two: Anchors
                .setVirtue(VirtueType.HOPE)
                .setVice(ViceType.PRIDE)

                // Step Three: Select Attributes
                // 2.1. Select priorities
                .setAttributesPriorities(CategoryType.MENTAL, CategoryType.SOCIAL, CategoryType.PHYSICAL)
                // 2.2. Set attributes
                .setMentalAttributes(3, 2, 0)
                .setSocialAttributes(2, 1, 1)
                .setPhysicalAttributes(0, 2, 1)

                // Step Four: Select Skills
                // 3.1. Select priorities
                .setSkillsPriorities(CategoryType.SOCIAL, CategoryType.MENTAL, CategoryType.PHYSICAL)
                // 3.2. Set skills
                .setSocialSkills(0, 5, 1, 0, 1, 2, 0, 1)
                .setMentalSkills(0, 2, 0, 3, 0, 2, 0, 0)
                .setPhysicalSkills(0, 0, 2, 0, 0, 2, 0, 0)

                // Step Five: Pick Skill Specialties
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3)
                // Step Six: Merits

                // Step Seven: Determine Advantages

                // Finally build the character
                ;
        Assertions.assertEquals(34, assembler.calculateSpentXp());

        final BaseCharacter character = assembler.assemble();
        Assertions.assertEquals(VirtueType.HOPE, character.getVirtue());
        Assertions.assertEquals(ViceType.PRIDE, character.getVice());
        Assertions.assertEquals(0, character.getExperiencePoints());
        Assertions.assertArrayEquals(
                new int[] { 4, 3, 1 },
                new int[]{
                        character.getAttribute(AttributeType.INTELLIGENCE),
                        character.getAttribute(AttributeType.WITS),
                        character.getAttribute(AttributeType.RESOLVE)
                });

        Assertions.assertArrayEquals(
                new int[] { 3, 2, 2 },
                new int[]{
                        character.getAttribute(AttributeType.PRESENCE),
                        character.getAttribute(AttributeType.MANIPULATION),
                        character.getAttribute(AttributeType.COMPOSURE)
                });

        Assertions.assertArrayEquals(
                new int[] { 1, 3, 2 },
                new int[]{
                        character.getAttribute(AttributeType.STRENGTH),
                        character.getAttribute(AttributeType.DEXTERITY),
                        character.getAttribute(AttributeType.STAMINA)
                });

        Assertions.assertArrayEquals(
                new int[] { 0, 5, 1, 0, 1, 2, 0, 1 },
                new int[]{
                        character.getSkill(SkillType.ANIMAL_KEN),
                        character.getSkill(SkillType.EMPATHY),
                        character.getSkill(SkillType.EXPRESSION),
                        character.getSkill(SkillType.INTIMIDATION),
                        character.getSkill(SkillType.PERSUASION),
                        character.getSkill(SkillType.SOCIALIZE),
                        character.getSkill(SkillType.STREETWISE),
                        character.getSkill(SkillType.SUBTERFUGE)
                });

        Assertions.assertArrayEquals(
                new int[] { 0, 2, 0, 3, 0, 2, 0, 0 },
                new int[]{
                        character.getSkill(SkillType.ACADEMICS),
                        character.getSkill(SkillType.COMPUTER),
                        character.getSkill(SkillType.CRAFTS),
                        character.getSkill(SkillType.INVESTIGATION),
                        character.getSkill(SkillType.MEDICINE),
                        character.getSkill(SkillType.OCCULT),
                        character.getSkill(SkillType.POLITICS),
                        character.getSkill(SkillType.SCIENCE)
                });

        Assertions.assertArrayEquals(
                new int[] { 0, 0, 2, 0, 0, 2, 0, 0 },
                new int[]{
                        character.getSkill(SkillType.ATHLETICS),
                        character.getSkill(SkillType.BRAWL),
                        character.getSkill(SkillType.DRIVE),
                        character.getSkill(SkillType.FIREARMS),
                        character.getSkill(SkillType.LARCENY),
                        character.getSkill(SkillType.STEALTH),
                        character.getSkill(SkillType.SURVIVAL),
                        character.getSkill(SkillType.WEAPONRY)
                });
    }

    @Test
    public void buildBeginnerCharacter_TooManyPointsPerCategory() throws CharacterException {
        final CharacterAssembler builder = CharacterAssembler.createBeginnerAssembler()
                .setAttributesPriorities(
                        CategoryType.MENTAL,
                        CategoryType.SOCIAL,
                        CategoryType.PHYSICAL)
                .setSkillsPriorities(
                        CategoryType.SOCIAL,
                        CategoryType.MENTAL,
                        CategoryType.PHYSICAL);

        Assertions.assertThrows(
                TooManyDotsPerCategoryException.class,
                () -> builder.setMentalAttributes(3, 2, 1));

        Assertions.assertThrows(
                TooManyDotsPerCategoryException.class,
                () -> builder.setSocialAttributes(2, 2, 1));

        Assertions.assertThrows(
                TooManyDotsPerCategoryException.class,
                () -> builder.setPhysicalAttributes(1, 2, 1));

        Assertions.assertThrows(
                TooManyDotsPerCategoryException.class,
                () -> builder.setSocialSkills(1, 3, 1, 0, 3, 2, 0, 2));

        Assertions.assertThrows(
                TooManyDotsPerCategoryException.class,
                () -> builder.setMentalSkills(0, 2, 1, 3, 0, 2, 0, 0));

        Assertions.assertThrows(
                TooManyDotsPerCategoryException.class,
                () -> builder.setPhysicalSkills(0, 0, 2, 0, 1, 2, 0, 0));
    }
}
