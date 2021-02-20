FROM node:12.17 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM node:12.17
WORKDIR /app
COPY --from=build /app .
CMD ["npx", "next", "start"]