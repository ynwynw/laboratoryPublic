import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/register', component: () => import('@/views/login/register'), hidden: true},
  {path: '/404', component: () => import('@/views/404'), hidden: true
},
  {
  path: '/', component: Layout, redirect: '/dashboard', children: [{
    path: 'dashboard',
    name: 'Dashboard',
    component: () => import('@/views/dashboard/index'),
    meta: {title: 'Dashboard', icon: 'dashboard'}
  }]
},

{
  path: '*', redirect: '/',
  hidden: true}
]
export const asyncRoutes = [
  {
    path: '/userSet',
    component: Layout,
    redirect: '/userSet/list',
    name: '用户管理',
    meta: {title: '用户管理', icon: 'example', roles: ['admin']},
    children: [{
      path: 'list', name: '用户设置列表', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/userset/list'), //跳转的页面路径
      meta: {title: '用户设置列表', icon: 'table'}
    }, {
      path: 'add', name: '用户设置添加', component: () => import('@/views/userset/add'), meta: {title: '用户设置添加', icon: 'tree'}
    }, {
      path: 'edit/:uid',
      name: '编辑',
      component: () => import('@/views/userset/add'),
      meta: {title: '编辑', noCache: true},
      hidden: true
    }]
  },
  {
    path: '/equipmentSet',
    component: Layout,
    redirect: '/equipmentSet/list',
    name: '设备管理',
    meta: {title: '设备管理', icon: 'example', roles: ['admin', 'teacher']},
    children: [{
      path: 'list', name: '设备列表', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/equipment/list'), //跳转的页面路径
      meta: {title: '设备列表', icon: 'table'}
    }, {
      path: 'add', name: '添加设备', component: () => import('@/views/equipment/add'),
      meta: {title: '设备添加', icon: 'tree', roles: ['admin']}
    }, {
      path: 'edit/:uid',
      name: '编辑',
      component: () => import('@/views/equipment/add'),
      meta: {title: '编辑', noCache: true},
      hidden: true
    }]
  },
  {
    path: '/lab',
    component: Layout,
    redirect: '/lab/list',
    name: '实验室管理',
    alwaysShow: true,
    meta: {title: '实验室查询', icon: 'example',roles: ['admin','teacher','user']},
    children: [{
      path: 'list', name: '实验室查询', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/lab/list'), //跳转的页面路径
      meta: {title: '实验室查询', icon: 'table'}
    }, {
      path: 'details/:uid',
      name: '详情',
      component: () => import('@/views/lab/details'),
      meta: {title: '实验室详情', noCache: true},
      hidden: true
    }, {
      path: 'appointment/:uid',
      name: '详情',
      component: () => import('@/views/lab/appointment'),
      meta: {title: '实验室预约', noCache: true,roles: ['admin','user']},
      hidden: true
    },

      {
        path: 'scheduled/:uid',
        name: '课表',
        component: () => import('@/views/lab/scheduled'),
        meta: {title: '课表', noCache: true },
        hidden: true
      }, {
        path: 'edit/:week',
        name: '编辑',
        component: () => import('@/views/lab/edit'),
        meta: {title: '编辑', noCache: true},
        hidden: true
      },
      {
        path: 'check',
        name: '审核',
        component: () => import('@/views/appointmentSet/check'),
        meta: {title: '预约审核', icon: 'table', roles: ['admin', 'teacher']},

      }, {
        path: 'old',
        name: '已审核信息',
        component: () => import('@/views/appointmentSet/old'),
        meta: {title: '已审核信息', noCache: true, roles: ['admin', 'teacher']},
        hidden: true
      }
    ]
  },

  {
    path: '/sta',
    component: Layout,
    redirect: '/cmn/list',
    name: '统计分析',
    alwaysShow: true,
    meta: {title: '统计分析', icon: 'example', roles: ['admin', 'teacher']},
    children: [{
      path: 'create', name: '生成数据', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/statistics/create'), //跳转的页面路径
      meta: {title: '生成数据', icon: 'table'}
    }, {
      path: 'show', name: '图表显示', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/statistics/show'), //跳转的页面路径
      meta: {title: '图表显示', icon: 'table'}
    }

    ]
  },

  // {
  //   path: '/appointmentSet',
  //   component: Layout,
  //   redirect: '/appointment/appointment',
  //   name: '预约管理',
  //   alwaysShow: true,
  //   meta: {title: '预约管理', icon: 'example', roles: ['admin', 'teacher']},
  //   children: [// {
  //     //   path: 'appointment',
  //     //   name: '实验室预约',
  //     //   // eslint-disable-next-line spaced-comment
  //     //   component: () => import('@/views/appointmentSet/appointment'), //跳转的页面路径
  //     //   meta: { title: '实验室预约', icon: 'table' }
  //     // },
  //     {
  //       path: 'check',
  //       name: '审核',
  //       component: () => import('@/views/appointmentSet/check'),
  //       meta: {title: '预约审核', icon: 'table'},
  //
  //     }, {
  //       path: 'old',
  //       name: '已审核信息',
  //       component: () => import('@/views/appointmentSet/old'),
  //       meta: {title: '已审核信息', noCache: true},
  //       hidden: true
  //     }
  //
  //   ]
  // },

  {
    path: '/notice',
    component: Layout,
    redirect: '/notice/list',
    name: '公告',
    alwaysShow: true,
    meta: {title: '公告', icon: 'example',roles: ['admin','teacher','user']},
    children: [{
      path: 'list', name: '公告列表', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/notice/list'), //跳转的页面路径
      meta: {title: '公告列表', icon: 'table',roles: ['admin','teacher','user']}
    }, {
      path: 'add', name: '添加公告', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/notice/add'), //跳转的页面路径
      meta: {title: '添加公告', icon: 'table',roles: ['admin']}
    }, {
      path: 'details/:nid', name: '公告详情', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/notice/details'), //跳转的页面路径
      meta: {title: '公告详情', icon: 'table',roles: ['admin','teacher','user']}, hidden: true
    }]
  },

  {
    path: '/course',
    component: Layout,
    redirect: '/notice/list',
    name: '课程',
    alwaysShow: true,
    meta: {title: '课程', icon:'example',roles: ['admin','user']},
    children: [{
      path: 'list', name: '课程列表', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/course/courseList'), //跳转的页面路径
      meta: {title: '课程列表', icon: 'table',roles: ['admin']}
    }, {
      path: 'add', name: '添加课程', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/course/addCourse'), //跳转的页面路径
      meta: {title: '添加课程', icon: 'table',roles: ['admin']}
    },
      {
        path: 'addcourse', name: '订阅课程', // eslint-disable-next-line spaced-comment
        component: () => import('@/views/course/deleteCourse'), //跳转的页面路径
        meta: {title: '订阅课程', icon: 'table',roles: ['user']}
      }
    ]
  },
  {
    path: '/lm',
    component: Layout,
    redirect: '/repair/list',
    name: '耗材',
    alwaysShow: true,
    meta: {title: '实验室耗材管理', icon:'example',roles: ['admin','teacher','user']},
    children: [{
      path: 'list', name: '材料列表', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/lm/list'), //跳转的页面路径
      meta: {title: '材料列表', icon: 'table'}
    },
      {
        path: 'delete', name: '材料删除', // eslint-disable-next-line spaced-comment
        component: () => import('@/views/lm/delete'), //跳转的页面路径
        meta: {title: '材料删除', icon: 'table',roles: ['admin']}
      },
      {
      path: 'add', name: '添加材料', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/lm/add'), //跳转的页面路径
      meta: {title: '添加材料', icon: 'table',roles: ['admin']}
    },
      {
        path: 'rlist', name: '报修列表', // eslint-disable-next-line spaced-comment
        component: () => import('@/views/repair/repairList'), //跳转的页面路径
        meta: {title: '报修列表', icon: 'table',roles: ['admin']}
      }, {
        path: 'radd', name: '报修', // eslint-disable-next-line spaced-comment
        component: () => import('@/views/repair/repair'), //跳转的页面路径
        meta: {title: '报修', icon: 'table',roles: ['teacher','user']}
      }
    ]
  },

  // {
  //   path: '/repair',
  //   component: Layout,
  //   redirect: '/repair/list',
  //   name: '报修',
  //   alwaysShow: true,
  //   meta: {title: '报修', icon:'example',roles: ['admin','teacher','user']},
  //   children: [{
  //     path: 'list', name: '报修列表', // eslint-disable-next-line spaced-comment
  //     component: () => import('@/views/repair/repairList'), //跳转的页面路径
  //     meta: {title: '报修列表', icon: 'table',roles: ['admin']}
  //   }, {
  //     path: 'add', name: '报修', // eslint-disable-next-line spaced-comment
  //     component: () => import('@/views/repair/repair'), //跳转的页面路径
  //     meta: {title: '报修', icon: 'table',roles: ['teacher','user']}
  //   }
  //   ]
  // },

  {
    path: '/my',
    component: Layout,
    redirect: '/my/appointment',
    name: '我的',
    alwaysShow: true,
    meta: {title: '我的', icon: 'example',roles: ['admin','teacher','user']},
    children: [{
      path: 'myinformation', name: '个人信息', // eslint-disable-next-line spaced-comment
      component: () => import('@/views/mySet/myInformation'), //跳转的页面路径
      meta: {title: '个人信息', icon: 'table'}
    }, {
      path: 'findAppointment',
      name: '预约查询',
      component: () => import('@/views/mySet/findAppointment'),
      meta: {title: '预约查询', icon: 'table',roles: ['user']},

    }, {
      path: 'progress/:uid',
      name: '详情',
      component: () => import('@/views/mySet/AppoProgress'),
      meta: {title: '预约进度', noCache: true},
      hidden: true
    }, {
      path: 'scList',
      name: '订阅列表',
      component: () => import('@/views/course/SCList'),
      meta: {title: '课程订阅列表', icon: 'table',roles: ['user']},
    }

    ]
  },

  {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}), routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
