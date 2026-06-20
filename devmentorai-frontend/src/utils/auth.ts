export function getToken() {
  return localStorage.getItem('devmentorai_token');
}

export function setToken(token: string) {
  localStorage.setItem('devmentorai_token', token);
}

export function clearToken() {
  localStorage.removeItem('devmentorai_token');
}
