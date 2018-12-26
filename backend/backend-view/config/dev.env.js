'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

//定义开发环境下的相关参数，构建时可以自动替换相关变量
module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  CONTEXT: '"/backend"',
  CONTEXT_HTML: '""'
})
