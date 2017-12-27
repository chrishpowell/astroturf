/*
 */
package eu.discoveri.astroturf3;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.TypeResolutionEnvironment;
import graphql.annotations.GraphQLDataFetcher;
import graphql.schema.DataFetcher;
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
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.type.NullType;
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
    
    public static void main(String[] args)
    {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        DataFetcher signsDataFetcher = new StaticDataFetcher("Test");
        File schemaFile = new File("/home/chrispowell/NetBeansProjects/AstroTurf3/src/main/java/eu/discoveri/astroturf3/a.graphqls");
        RuntimeWiring wiring = newRuntimeWiring()
                .type("Entity", typeWiring -> typeWiring
                    .typeResolver(resolveEntity()))
                .type("LengthUnit", typeWiring -> typeWiring
                    .enumValues(LUResolver))
                .type("QueryEndPoint", typeWiring -> typeWiring
                   .dataFetcher("signs", signsDataFetcher))
                .build();
        
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

//        String GQLquery = "{\nastrology(id:9){\nname\n signs{\nid\n name\n}\n}\n}";
//        ExecutionInput execInput = ExecutionInput.newExecutionInput().query(GQLquery).build();
//        ExecutionResult execResult = graphQL.execute(execInput);
//        Object data = execResult.getData();
//        List<GraphQLError> errors = execResult.getErrors();
    }
}
