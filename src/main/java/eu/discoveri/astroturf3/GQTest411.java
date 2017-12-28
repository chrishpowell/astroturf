/*
 */
package eu.discoveri.astroturf3;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.TypeResolutionEnvironment;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLObjectType;
import graphql.schema.StaticDataFetcher;
import graphql.schema.TypeResolver;
import graphql.schema.idl.EnumValuesProvider;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;

import java.io.File;
import java.util.List;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author chrispowell
 */
@WebServlet("/astrology")
public class GQTest411 extends SimpleGraphQLServlet.Builder
{
    public GQTest411(GraphQLSchema schema) {
        super(schema);
    }

    private static EnumValuesProvider LUResolver = LengthUnit::valueOf;
    private static TypeResolver resolveEntity()
    {
        return new TypeResolver()
        {
            @Override
            public GraphQLObjectType getType(TypeResolutionEnvironment env)
            {
                if( env.getObject() instanceof Person) { return (GraphQLObjectType)env.getSchema().getType("Person"); }
                return null;
            }    
        };
    }
    private static DataFetcher signsDataFetcher = new SignDataFetcher();
    private static DataFetcher personDataFetcher = new PersonDataFetcher();
    
    
    /**
     * M A I N
     * -------
     * @param args 
     */
    public static void main(String[] args)
    {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        
        File schemaFile = new File("/home/chrispowell/NetBeansProjects/AstroTurf3/src/main/java/eu/discoveri/astroturf3/a.graphqls");
        RuntimeWiring wiring = newRuntimeWiring()
                .type("Entity", typeWiring -> typeWiring.typeResolver(resolveEntity()))
                .type("LengthUnit", typeWiring -> typeWiring.enumValues(LUResolver))
                .type("Query1", typeWiring -> typeWiring.dataFetcher("person", personDataFetcher)) // "QueryEndPoint"  .dataFetcher("signs", signsDataFetcher))
                .build();
        
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

        String GQLquery = "{\nperson(id:\"fred\",identifier:\"EH\"){\nidentifier\n}\n}";  //{\nastrology(id:9){\nname\n signs{\nid\n name\n}\n}\n}";
        ExecutionInput execInput = ExecutionInput.newExecutionInput().query(GQLquery).build();
        ExecutionResult execResult = graphQL.execute(execInput);
        Object data = execResult.getData();
        
        List<GraphQLError> errors = execResult.getErrors();
        if( errors.isEmpty() )
            {  System.out.println("JSON>>> " +data); }
        else
        {
            System.out.println("***Errors:");
            for( GraphQLError gqlErr: errors )
                { System.out.println("  " +gqlErr.getMessage()); }
        }
    }
}

