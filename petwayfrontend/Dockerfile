FROM node:14.2.0-alpine as petwayfrontend
WORKDIR /app
COPY package.json /app
RUN npm install --silent
COPY . .
RUN npm run build

FROM nginx:alpine
VOLUME /var/cache/nginx
COPY --from=petwayfrontend app/dist/petwayfrontend /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf
