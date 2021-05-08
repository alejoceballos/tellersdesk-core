package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.MANIPULATION, AttributeType.COMPOSURE },
        categories = CategoryType.SOCIAL,
        type = ActionType.REFLEXIVE
)
class PokerFaceTest extends AttributeTaskTest<PokerFace> {
}
