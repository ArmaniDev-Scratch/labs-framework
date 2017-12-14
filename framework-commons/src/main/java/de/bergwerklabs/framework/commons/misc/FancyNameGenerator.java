package de.bergwerklabs.framework.commons.misc;

import de.bergwerklabs.framework.commons.database.tablebuilder.Database;
import de.bergwerklabs.framework.commons.database.tablebuilder.DatabaseType;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Row;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.StatementResult;

import java.util.*;

public class FancyNameGenerator {

    private static class Noun {
        public final String noun;
        public final String article;

        Noun(String noun, String article) {
            this.noun = noun;
            this.article = article;
        }
    }

    private static final Database DATABASE = new Database(DatabaseType.MySQL, "sql.bergwerklabs.de", "fancy_name_generator", "fancy-name-generator", "XCVPg5CyiNGKSSh1ZFG1lLW4Br8kg1Ci");

    private static final List<String> ADJECTIVES = new ArrayList<>();
    private static final List<Noun> NOUNS = new ArrayList<>();

    static {
        Statement nounStatement = DATABASE.prepareStatement("SELECT noun, gender FROM nouns WHERE active = 1");
        StatementResult nounResult = nounStatement.execute();
        nounStatement.close();

        for (Row row : nounResult.getRows()) {
            NOUNS.add(new Noun(
                    row.getString("noun"),
                    getArticle(row.getString("gender"))
            ));
        }

        Statement adjectiveStatement = DATABASE.prepareStatement("SELECT adjective FROM adjectives WHERE active = 1");
        StatementResult adjectiveResult = adjectiveStatement.execute();
        adjectiveStatement.close();

        for (Row row : adjectiveResult.getRows()) {
            ADJECTIVES.add(row.getString("adjective"));
        }
    }

    private static String getArticle(String gender) {
        switch (gender) {
            case "MASCULINE": return "der";
            case "FEMININE": return "die";
            default: return "das";
        }
    }

    public static String generate(long seed) {
        Random random = new Random(Math.abs(seed));
        Noun noun = NOUNS.get(random.nextInt(NOUNS.size()));
        String adjective = ADJECTIVES.get(random.nextInt(ADJECTIVES.size()));
        return noun.article.toLowerCase() + "_" + adjective.toLowerCase() + "_" + noun.noun.toLowerCase();
    }

    public static String generate(String value) {
        return generate(value.hashCode());
    }
}
