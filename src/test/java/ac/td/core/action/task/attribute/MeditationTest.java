package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.RESOLVE, AttributeType.COMPOSURE },
        categories = CategoryType.MENTAL,
        type = ActionType.EXTENDED
)
class MeditationTest extends AttributeTaskTest<Meditation> {
}
