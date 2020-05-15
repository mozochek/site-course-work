<#assign knowContext = Session.SPRING_SECURITY_CONTEXT??>

<#if knowContext>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = user.username
        isModerator = user.isModerator()
        isAdmin = user.isAdmin()
        isDeveloper = user.isDeveloper()
    >
</#if>