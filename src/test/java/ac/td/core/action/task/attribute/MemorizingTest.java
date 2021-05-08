package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.INTELLIGENCE, AttributeType.COMPOSURE },
        categories = CategoryType.MENTAL,
        type = ActionType.INSTANT
)
class MemorizingTest extends AttributeTaskTest<Memorizing> {
}
