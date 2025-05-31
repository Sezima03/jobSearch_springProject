<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-bg: #121212;
            --text-light: #e0e0e0;
            --text-muted: #888888;
        }

        body {
            background-color: var(--dark-bg);
            color: var(--text-light);
        }

        .card {
            background-color: var(--dark-bg);
            border: 1px solid var(--soft-yellow);
        }

        .card-header {
            background-color: var(--dark-bg);
            color: var(--soft-yellow);
            font-weight: bold;
        }

        .card-body {
            color: var(--text-light);
        }

        .card-container {
            max-width: 1000px;
            margin: 80px auto 20px auto;
        }

    </style>

    <div class="card-container">
        <h2>Ваши отклики</h2>

            <div class="card">
                 <#list responses as respondedApplicant>
                            <div class="card">
                                <div class="card-header">
                                    <@spring.message "resume"/>${respondedApplicant.resumeName}
                                </div>
                                <div class="card-body">
                                    <p><strong><@spring.message "vacancy"/></strong> ${respondedApplicant.vacancyName}</p>
                                    <p><strong><@spring.message "status"/>:</strong>
                                        <#if respondedApplicant.confirmation>
                                            <@spring.message "confirmed"/>
                                        <#else>
                                            <@spring.message "notconfirmed"/>
                                        </#if>
                                    </p>
                                </div>
                            </div>
                        </#list>

                </div>
            </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>

</@main.layout>
