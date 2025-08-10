// gateway/gateway.js
const { ApolloGateway } = require('@apollo/gateway');
const { ApolloServer } = require('@apollo/server');
const { expressMiddleware } = require('@apollo/server/express4');
const express = require('express');
const { json } = require('body-parser');
const { ApolloServerPluginLandingPageGraphQLPlayground } = require('apollo-server-core');

const startServer = async () => {
  const app = express();

  const gateway = new ApolloGateway({
    serviceList: [
      { name: 'invoice-service', url: 'http://127.0.0.1:8091/graphql' },
      { name: 'purchase-order-service', url: 'http://127.0.0.1:8082/graphql' }
    ]
  });

  const server = new ApolloServer({
    gateway,
    plugins: [ApolloServerPluginLandingPageGraphQLPlayground()],
    // No subscriptions option in v4
  });

  await server.start();
  app.use('/graphql', json(), expressMiddleware(server));
  app.listen({ port: 4000 }, () => {
    console.log('🚀 Gateway ready at http://localhost:4000/graphql');
  });
};

startServer();