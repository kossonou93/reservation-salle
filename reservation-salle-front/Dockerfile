# Utiliser une image Node.js comme base
#FROM node:16.3.0-alpine
#WORKDIR /app
#COPY package.json #.
#COPY package-lock.json .
#RUN npm install
#COPY . .
#RUN npm run build
#EXPOSE 5173
#CMD ["npm", "run", "start"]

FROM node:alpine3.16 as nodework
WORKDIR /reservation-salle-front
COPY package.json .
RUN npm install --force
COPY . .
RUN npm run build

# release etape
FROM nginx:1.25.1-alpine3.17
WORKDIR /usr/share/nginx/html/
RUN rm -rf ./*
COPY --from=nodework /reservation-salle-front/build .
ENTRYPOINT  ["nginx", "-g", "daemon off;"]
EXPOSE 80
