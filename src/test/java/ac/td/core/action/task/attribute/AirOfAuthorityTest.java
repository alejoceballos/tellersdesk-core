package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.PRESENCE, AttributeType.INTELLIGENCE },
        categories = CategoryType.SOCIAL,
        type = ActionType.INSTANT
)
class AirOfAuthorityTest extends AttributeTaskTest<AirOfAuthority> {
}
